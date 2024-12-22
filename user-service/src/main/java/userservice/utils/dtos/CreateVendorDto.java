package userservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.interfaces.MyDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVendorDto implements MyDto {
    private String username;
    private String email;
    private String password;
    private String vendorName;
    private String bankAccount;
    private String pib;
}
