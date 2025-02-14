package userservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.entities.embedded.Address;
import userservice.entities.embedded.CardInfo;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto implements Serializable {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private CardInfo cardInfo;
}
