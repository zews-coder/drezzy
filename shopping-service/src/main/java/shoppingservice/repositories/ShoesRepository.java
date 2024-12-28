package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.ArticleShoes;

@Repository
public interface ShoesRepository extends JpaRepository<ArticleShoes, Long> {
}
