package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.ArticleAccessories;

@Repository
public interface AccessoriesRepository extends JpaRepository<ArticleAccessories, Long> {
}
