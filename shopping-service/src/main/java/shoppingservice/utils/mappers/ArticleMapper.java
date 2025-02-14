package shoppingservice.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import shoppingservice.enitites.articles.ArticleShoes;
import shoppingservice.utils.dtos.articles.ArticlesWithImageDto;
import shoppingservice.utils.dtos.CustomerArticleDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    CustomerArticleDto shoesArticleToCustomerArticleDto(ArticleShoes articleShoes);

    ArticlesWithImageDto shoesToArticlesWithImageDto(ArticleShoes articleShoes);
}
