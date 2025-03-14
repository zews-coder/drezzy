package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shoppingservice.enitites.articles.ArticleShoes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeShoes;
import shoppingservice.repositories.ShoesRepository;
import shoppingservice.utils.dtos.articles.ArticlesWithImageDto;
import shoppingservice.utils.dtos.clothes.CreateShoesDto;
import shoppingservice.utils.dtos.CustomerArticleDto;
import shoppingservice.utils.mappers.ArticleMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ShoesService {
    private ShoesRepository shoesRepository;

    public CustomerArticleDto findById(Long id) {
        ArticleShoes articleShoes = shoesRepository.findById(id).orElse(null);
        if (articleShoes == null) {
            return null;
        }
        articleShoes.setVisited(articleShoes.getVisited()+1);
        shoesRepository.save(articleShoes);
        CustomerArticleDto customerArticleDto = ArticleMapper.INSTANCE.shoesArticleToCustomerArticleDto(articleShoes);
        customerArticleDto.setId(articleShoes.getId());
        customerArticleDto.setTitle(articleShoes.getTitle());
        customerArticleDto.setDescription(articleShoes.getDescription());
        customerArticleDto.setPrice(articleShoes.getPrice());
        customerArticleDto.setDiscount(articleShoes.getDiscount());
        customerArticleDto.setSize(String.valueOf(articleShoes.getSize()));
        if (articleShoes.getImage() != null) {
            customerArticleDto.setImageUrl(Base64.getEncoder().encodeToString(articleShoes.getImage()));
        }
        return customerArticleDto;
    }

    public List<ArticlesWithImageDto> findBySubtype (String subtype) {
        return convertList(shoesRepository.findAllBySubtypeAndSexAndVisible(SubtypeShoes.valueOf(subtype), Sex.MEN, true));
    }

    public List<ArticlesWithImageDto> findAllNewest(){
        return convertList(shoesRepository.findTop10BySexAndVisibleOrderByCreationDateDesc(Sex.MEN,true));
    }

    public List<ArticlesWithImageDto> findAllOnDiscount(){
        return convertList(shoesRepository.findAllBySexAndDiscountGreaterThanAndVisible(Sex.MEN, 0, true));
    }

    public List<ArticlesWithImageDto> mostVisited(){
        return convertList(shoesRepository.findTop10BySexAndVisibleOrderByVisitedDesc(Sex.MEN, true));
    }

    private List<ArticlesWithImageDto> convertList(List<ArticleShoes> articleShoesList) {
        List<ArticlesWithImageDto> articlesWithImageDtoList = new ArrayList<>();

        for (ArticleShoes articleShoes : articleShoesList) {
            ArticlesWithImageDto articlesWithImageDto = ArticleMapper.INSTANCE.shoesToArticlesWithImageDto(articleShoes);
            articlesWithImageDto.setId(articleShoes.getId());
            articlesWithImageDto.setTitle(articleShoes.getTitle());
            articlesWithImageDto.setDescription(articleShoes.getDescription());
            articlesWithImageDto.setCreationDate(articleShoes.getCreationDate());
            articlesWithImageDto.setPrice(articleShoes.getPrice());
            articlesWithImageDto.setDiscount(articleShoes.getDiscount());
            articlesWithImageDto.setSex(articleShoes.getSex());
            articlesWithImageDto.setVisible(articleShoes.getVisible());
            articlesWithImageDto.setSubtype(articleShoes.getSubtype().toString());
            articlesWithImageDto.setSize(String.valueOf(articleShoes.getSize()));
            if (articleShoes.getImage() != null) {
                articlesWithImageDto.setImageUrl(Base64.getEncoder().encodeToString(articleShoes.getImage()));
            }

            articlesWithImageDtoList.add(articlesWithImageDto);
        }

        return articlesWithImageDtoList;
    }

    public ArticleShoes addShoes(CreateShoesDto createShoesDto, MultipartFile image) throws IOException {
        ArticleShoes articleShoes = new ArticleShoes();
        articleShoes.setTitle(createShoesDto.getTitle());
        articleShoes.setDescription(createShoesDto.getDescription());
        articleShoes.setPrice(createShoesDto.getPrice());
        articleShoes.setDiscount(0);
        articleShoes.setSex(Sex.valueOf(createShoesDto.getSex().toString()));
        articleShoes.setSubtype(SubtypeShoes.valueOf(createShoesDto.getSubtype_shoes().toString()));
        articleShoes.setSize(createShoesDto.getSize());
        articleShoes.setCreationDate(new Date());
        if (image != null) {
            articleShoes.setImage(image.getBytes());
        }
        return shoesRepository.save(articleShoes);
    }

    public void delete(Long id) {
        shoesRepository.deleteById(id);
    }

    public void empty() {
        shoesRepository.deleteAll();
    }
}
