package userservice.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.interfaces.MyDto;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDto implements MyDto {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String cardNumber;
}
