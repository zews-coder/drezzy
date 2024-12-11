package userservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userservice.domain.LoginDto;
import userservice.services.CustomerService;
import userservice.services.UserService;
import userservice.services.VendorService;
import userservice.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final JwtUtils jwtUtils;

    //login user
    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto){
        try {
            if (userService.userLogin(loginDto.getEmail(), loginDto.getPassword())){
                return ResponseEntity.ok(jwtUtils.generateToken(userService.getUserByEmail(loginDto.getEmail())));
            }else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }


    }

    //login customer
    @PostMapping("/customer")
    public ResponseEntity<?> customerLogin(@RequestBody LoginDto loginDto){
        return null;
    }

    //login vendor
    @PostMapping("/vendor")
    public ResponseEntity<?> vendorLogin(@RequestBody LoginDto loginDto){
        return null;
    }
}
