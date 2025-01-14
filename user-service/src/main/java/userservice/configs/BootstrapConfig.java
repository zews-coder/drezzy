package userservice.configs;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import userservice.entities.Customer;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.repositories.CustomerRepository;
import userservice.repositories.UserRepository;

@Component
@AllArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //creating admin
        if (userRepository.findByRole(Role.ADMIN).stream().findAny().isEmpty()) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setIsActive(true);
            userRepository.save(user);
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
