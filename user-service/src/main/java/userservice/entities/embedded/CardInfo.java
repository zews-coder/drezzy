package userservice.entities.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo implements Serializable {
    private String cardHolder;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
