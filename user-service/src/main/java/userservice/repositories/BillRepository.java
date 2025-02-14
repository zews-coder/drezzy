package userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userservice.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
