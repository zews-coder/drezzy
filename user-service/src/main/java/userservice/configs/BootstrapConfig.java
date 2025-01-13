package userservice.configs;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.entities.Vendor;
import userservice.repositories.CustomerRepository;
import userservice.repositories.UserRepository;
import userservice.repositories.VendorRepository;

@Component
@AllArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setEmail("admin@admin.com");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setIsActive(true);
//            user.setId(1);
            userRepository.save(user);
        }

        if (vendorRepository.count() == 0) {
            Vendor vendor = new Vendor();
            vendor.setEmail("vendor@test.com");
            vendor.setUsername("test_vendor");
            vendor.setPassword(passwordEncoder.encode("vendor"));
            vendor.setRole(Role.VENDOR);
            vendor.setIsActive(true);
            vendor.setVendorName("Test vendor");
            vendor.setPib("12345678");
            vendor.setBankAccount("1111222");
//            vendor.setId(2);
            vendorRepository.save(vendor);
        }

        if (customerRepository.count() == 0) {
            Customer customer = new Customer();
            customer.setEmail("customer@test.com");
            customer.setUsername("test_customer");
            customer.setPassword(passwordEncoder.encode("customer"));
            customer.setRole(Role.CUSTOMER);
            customer.setIsActive(true);
            customer.setFirstName("John");
            customer.setLastName("Smith");
            customer.setAddress("Jasenicka");
            customer.setCardNumber("12341234");
            customerRepository.save(customer);
        }
    }

}
