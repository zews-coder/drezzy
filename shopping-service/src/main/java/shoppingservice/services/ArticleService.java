package shoppingservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingservice.enitites.Article;
import shoppingservice.enitites.ArticleClothes;
import shoppingservice.enitites.ArticleShoes;
import shoppingservice.repositories.ArticleRepository;
import shoppingservice.repositories.ClothesRepository;
import shoppingservice.repositories.ShoesRepository;
import shoppingservice.utils.dtos.ArticlesWithImageDto;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ClothesRepository clothesRepository;
    private final ShoesRepository shoesRepository;

    public List<ArticlesWithImageDto> getAllArticles() {
        List<ArticlesWithImageDto> articlesWithImageDtos = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();
        for (Article article : articles) {
            ArticlesWithImageDto articlesWithImageDto = new ArticlesWithImageDto();
            articlesWithImageDto.setId(article.getId());
            articlesWithImageDto.setTitle(article.getTitle());
            articlesWithImageDto.setDescription(article.getDescription());
            articlesWithImageDto.setCreationDate(article.getCreationDate());
            articlesWithImageDto.setPrice(article.getPrice());
            articlesWithImageDto.setDescription(article.getDescription());
            articlesWithImageDto.setSex(article.getSex());
            articlesWithImageDto.setVisible(article.getVisible());
            articlesWithImageDto.setDiscount(article.getDiscount());
            if (clothesRepository.findById(article.getId()).isPresent()) {
                ArticleClothes articleClothes = clothesRepository.findById(article.getId()).get();
                articlesWithImageDto.setSubtype(articleClothes.getSubtype().toString());
                articlesWithImageDto.setSize(articleClothes.getSize());
            }else if (shoesRepository.findById(article.getId()).isPresent()) {
                ArticleShoes articleShoes = shoesRepository.findById(article.getId()).get();
                articlesWithImageDto.setSubtype(articleShoes.getSubtype().toString());
                articlesWithImageDto.setSize(articleShoes.getSize().toString());
            }
            if (article.getImage() != null) {
                articlesWithImageDto.setImageUrl(Base64.getEncoder().encodeToString(article.getImage()));
            }else {
                articlesWithImageDto.setImageUrl(null);
            }
            articlesWithImageDtos.add(articlesWithImageDto);
        }

        return articlesWithImageDtos;
    }

    public void changeArticleVisibility(Long id){
        articleRepository.findById(id).ifPresent(article -> {
            article.setVisible(!article.getVisible());
            articleRepository.save(article);
        });
    }

    public void setDiscount(Long id, Integer discount) {
        articleRepository.findById(id).ifPresent(article -> {
            article.setDiscount(discount);
            articleRepository.save(article);
        });
    }
}
