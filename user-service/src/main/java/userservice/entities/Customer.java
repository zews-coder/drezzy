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
public class Customer extends User implements MyEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String cardNumber;
}
