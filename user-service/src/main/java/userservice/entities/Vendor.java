package userservice.entities;

import jakarta.persistence.Entity;
import lombok.*;
import userservice.interfaces.MyEntity;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor extends User implements MyEntity {
    private String vendorName;
    private String bankAccount;
    private String pib;
}
