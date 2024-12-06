package userservice.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor extends User {
    private String vendorName;
    private String bankAccount;
    private String pib;
}
