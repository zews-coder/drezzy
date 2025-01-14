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

        //two customers
        if (customerRepository.count() == 0) {
            Customer customer1 = new Customer();
            customer1.setEmail("customer1@gmail.com");
            customer1.setUsername("customer1");
            customer1.setPassword(passwordEncoder.encode("customer1"));
            customer1.setRole(Role.CUSTOMER);
            customer1.setIsActive(true);
            customer1.setFirstName("Djoko");
            customer1.setLastName("Cvarkov");
            customer1.setAddress("Peicevi Salasi");
            customer1.setCardNumber("12341234");
            customerRepository.save(customer1);

            Customer customer2 = new Customer();
            customer2.setEmail("customer2@gmail.com");
            customer2.setUsername("customer2");
            customer2.setPassword(passwordEncoder.encode("customer2"));
            customer2.setRole(Role.CUSTOMER);
            customer2.setIsActive(true);
            customer2.setFirstName("Dragan");
            customer2.setLastName("Torbica");
            customer2.setAddress("Laze Stajica");
            customer2.setCardNumber("56785678");
            customerRepository.save(customer2);
        }
    }
}
