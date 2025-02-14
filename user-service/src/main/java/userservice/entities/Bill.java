package userservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.entities.embedded.Address;
import userservice.entities.embedded.CardInfo;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;
    @ManyToOne
    @JoinColumn(name = "id")
    private User customerId;
    @Embedded
    private Address address;
    @Embedded
    private CardInfo cardInfo;
    private Long date;
    private Double price;
}
