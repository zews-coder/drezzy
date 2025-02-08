package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shoppingservice.enitites.ArticleClothes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;
import shoppingservice.repositories.ClothesRepository;
import shoppingservice.utils.dtos.ArticlesWithImageDto;
import shoppingservice.utils.dtos.CreateClothesDto;
import shoppingservice.utils.dtos.CustomerArticleDto;
import shoppingservice.utils.mappers.ArticleMapper;
import shoppingservice.utils.mappers.ClothesArticleMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public CustomerArticleDto findById(Long id) {
        ArticleClothes articleClothes = clothesRepository.findById(id).orElse(null);
        if (articleClothes == null) {
            return null;
        }
        articleClothes.setVisited(articleClothes.getVisited() + 1);
        clothesRepository.save(articleClothes);
        CustomerArticleDto customerArticleDto = new CustomerArticleDto();
        customerArticleDto.setId(articleClothes.getId());
        customerArticleDto.setTitle(articleClothes.getTitle());
        customerArticleDto.setDescription(articleClothes.getDescription());
        customerArticleDto.setPrice(articleClothes.getPrice());
        customerArticleDto.setDiscount(articleClothes.getDiscount());
        customerArticleDto.setSize(articleClothes.getSize());
        customerArticleDto.setImageUrl(null);
        if (articleClothes.getImage() != null) {
            customerArticleDto.setImageUrl(Base64.getEncoder().encodeToString(articleClothes.getImage()));
        }
        return customerArticleDto;
    }

    public List<ArticlesWithImageDto> findAllNewest(){
        return convertList(clothesRepository.findTop10BySexAndVisibleOrderByCreationDateDesc(Sex.MEN,true));
    }

    public List<ArticlesWithImageDto> findAllOnDiscount(){
        return convertList(clothesRepository.findAllBySexAndDiscountGreaterThanAndVisible(Sex.MEN, 0, true));
    }

    public List<ArticlesWithImageDto> findBySubtype (String subtype) {
        return convertList(clothesRepository.findAllBySubtypeAndSexAndVisible(SubtypeClothes.valueOf(subtype), Sex.MEN, true));
    }

    public List<ArticlesWithImageDto> mostVisited(){
        return convertList(clothesRepository.findTop10BySexAndVisibleOrderByVisitedDesc(Sex.MEN, true));
    }

    private List<ArticlesWithImageDto> convertList(List<ArticleClothes> articleClothesList) {
        List<ArticlesWithImageDto> articlesWithImageDtoList = new ArrayList<>();

        for (ArticleClothes articleClothes : articleClothesList) {
            ArticlesWithImageDto articlesWithImageDto = ClothesArticleMapper.INSTANCE.articleToArticlesWithImageDto(articleClothes);
            articlesWithImageDto.setId(articleClothes.getId());
            articlesWithImageDto.setTitle(articleClothes.getTitle());
            articlesWithImageDto.setDescription(articleClothes.getDescription());
            articlesWithImageDto.setCreationDate(articleClothes.getCreationDate());
            articlesWithImageDto.setPrice(articleClothes.getPrice());
            articlesWithImageDto.setDiscount(articleClothes.getDiscount());
            articlesWithImageDto.setSex(articleClothes.getSex());
            articlesWithImageDto.setVisible(articleClothes.getVisible());
            articlesWithImageDto.setSubtype(articleClothes.getSubtype().toString());
            articlesWithImageDto.setSize(articleClothes.getSize());
            if (articleClothes.getImage() != null) {
                articlesWithImageDto.setImageUrl(Base64.getEncoder().encodeToString(articleClothes.getImage()));
            }

            articlesWithImageDtoList.add(articlesWithImageDto);
        }

        return articlesWithImageDtoList;
    }

    public ArticleClothes addClothes(CreateClothesDto createClothesDto, MultipartFile image) throws IOException {
        ArticleClothes articleClothes = new ArticleClothes();
        articleClothes.setTitle(createClothesDto.getTitle());
        articleClothes.setDescription(createClothesDto.getDescription());
        articleClothes.setPrice(createClothesDto.getPrice());
        articleClothes.setDiscount(createClothesDto.getDiscount());
        articleClothes.setSex(Sex.valueOf(createClothesDto.getSex().toString()));
        articleClothes.setSubtype(SubtypeClothes.valueOf(createClothesDto.getSubtype_clothes().toString()));
        articleClothes.setSize(createClothesDto.getSize());
        articleClothes.setCreationDate(new Date());
        if (image != null) {
            articleClothes.setImage(image.getBytes());
        }
        return clothesRepository.save(articleClothes);
    }

    public void delete(Long id) {
        clothesRepository.deleteById(id);
    }

    public void empty() {
        clothesRepository.deleteAll();
    }
}
