package shoppingservice.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import shoppingservice.enitites.articles.ArticleClothes;
import shoppingservice.utils.dtos.articles.ArticlesWithImageDto;
import shoppingservice.utils.dtos.CustomerArticleDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClothesArticleMapper {
    ClothesArticleMapper INSTANCE = Mappers.getMapper(ClothesArticleMapper.class);

    CustomerArticleDto articleToCustomerArticleDto(ArticleClothes article);

    ArticlesWithImageDto articleToArticlesWithImageDto(ArticleClothes article);
}
