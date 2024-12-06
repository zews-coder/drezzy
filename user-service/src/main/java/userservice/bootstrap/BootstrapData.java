package userservice.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import userservice.entities.Role;
import userservice.entities.User;
import userservice.repositories.UserRepository;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@admin.com");
            user.setPassword("admin");
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }
}
