package userservice.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User implements Serializable {
    private String username;
    private String firstName;
    private String lastName;
    @Embedded
    private Address address;
    @Embedded
    private CardInfo cardInfo;
}
