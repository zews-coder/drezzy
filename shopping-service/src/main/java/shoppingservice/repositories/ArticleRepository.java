package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.articles.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
