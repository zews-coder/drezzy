package userservice.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Vendor extends User {
    private String vendorName;
    private String bankAccount;
    private String owner;
    private String pib;
}
