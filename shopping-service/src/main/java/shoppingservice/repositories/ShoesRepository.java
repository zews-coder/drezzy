package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.Article;
import shoppingservice.enitites.ArticleShoes;

import java.util.Optional;

@Repository
public interface ShoesRepository extends JpaRepository<ArticleShoes, Long> {
    Optional<ArticleShoes> findById(Long id);
}
