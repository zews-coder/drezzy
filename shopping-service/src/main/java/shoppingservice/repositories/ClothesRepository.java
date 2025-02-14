package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.articles.ArticleClothes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesRepository extends JpaRepository<ArticleClothes,Long> {
    Optional<ArticleClothes> findById(Long id);

    List<ArticleClothes> findAllBySubtypeAndSexAndVisible(SubtypeClothes subtype, Sex sex, Boolean visible);

    List<ArticleClothes> findAllBySexAndDiscountGreaterThanAndVisible(Sex sex, Integer discountIsGreaterThan, Boolean visible);

    List<ArticleClothes> findTop10BySexAndVisibleOrderByVisitedDesc(Sex sex, boolean visible);

    List<ArticleClothes> findTop10BySexAndVisibleOrderByCreationDateDesc(Sex sex, boolean visible);
}
