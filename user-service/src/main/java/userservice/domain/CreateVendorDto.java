package userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVendorDto implements Serializable {
    private String username;
    private String email;
    private String password;
    private String vendorName;
    private String bankAccount;
    private String pib;
}
