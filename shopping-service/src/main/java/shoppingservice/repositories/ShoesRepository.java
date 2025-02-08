package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.ArticleShoes;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeShoes;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoesRepository extends JpaRepository<ArticleShoes, Long> {
    Optional<ArticleShoes> findById(Long id);

    List<ArticleShoes> findAllBySubtypeAndSexAndVisible(SubtypeShoes subtype, Sex sex, Boolean visible);

    List<ArticleShoes> findAllBySexAndDiscountGreaterThanAndVisible(Sex sex, Integer discountIsGreaterThan, Boolean visible);

    List<ArticleShoes> findTop10BySexAndVisibleOrderByVisitedDesc(Sex sex, boolean visible);

    List<ArticleShoes> findTop10BySexAndVisibleOrderByCreationDateDesc(Sex sex, boolean visible);
}
