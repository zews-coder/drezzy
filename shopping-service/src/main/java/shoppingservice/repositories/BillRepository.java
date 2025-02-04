package shoppingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shoppingservice.enitites.Bill;
import shoppingservice.enitites.enums.Status;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByStatus(Status status);
    List<Bill> findByCustomerId(Long customerId);
    int countByStatus(Status status);
}
