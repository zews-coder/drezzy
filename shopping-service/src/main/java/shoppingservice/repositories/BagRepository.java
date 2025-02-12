package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.Bag;

import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
    Optional<Bag> findByUserId(Long userId);
}
