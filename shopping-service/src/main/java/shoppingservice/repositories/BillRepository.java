package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
