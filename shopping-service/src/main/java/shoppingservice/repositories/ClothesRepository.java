package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.Article;
import shoppingservice.enitites.ArticleClothes;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<ArticleClothes,Long> {

}
