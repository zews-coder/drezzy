package userservice.configs;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import userservice.entities.*;
import userservice.entities.embedded.Address;
import userservice.entities.embedded.CardInfo;
import userservice.entities.enums.Role;
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
        if (userRepository.findByRole(Role.ADMIN).stream().findAny().isEmpty()) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setIsActive(true);
            userRepository.save(user);
        }

        if (customerRepository.count() == 0) {
            Customer customer1 = new Customer();
            customer1.setEmail("customer1@gmail.com");
            customer1.setUsername("customer1");
            customer1.setPassword(passwordEncoder.encode("customer1"));
            customer1.setRole(Role.CUSTOMER);
            customer1.setIsActive(true);
            customer1.setFirstName("Djoko");
            customer1.setLastName("Cvarkov");

            Address address1 = new Address();
            address1.setStreet("Vojvode Vlahovica");
            address1.setCity("Belgrade");
            address1.setState("Serbia");
            address1.setZip("11000");
            address1.setPhone("063603953");
            customer1.setAddress(address1);

            CardInfo cardInfo1 = new CardInfo();
            cardInfo1.setCardNumber("1234123412341234");
            cardInfo1.setCardHolder("Djoko Cvarkov");
            cardInfo1.setExpiryDate("03/27");
            cardInfo1.setCvv("357");
            customer1.setCardInfo(cardInfo1);

            customerRepository.save(customer1);
        }
    }
}
