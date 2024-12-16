package userservice.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.repositories.UserRepository;

@Component
@AllArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            User user = new User();
            user.setEmail("admin@admin.com");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setIsActive(true);

            userRepository.save(user);
        }
    }
}
