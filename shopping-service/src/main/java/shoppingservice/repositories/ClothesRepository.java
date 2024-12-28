package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.ArticleClothes;

@Repository
public interface ClothesRepository extends JpaRepository<ArticleClothes,Long> {
}
