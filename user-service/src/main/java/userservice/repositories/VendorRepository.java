package userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userservice.entities.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
