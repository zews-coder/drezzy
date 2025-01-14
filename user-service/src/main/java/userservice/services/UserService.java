package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import userservice.entities.User;
import userservice.repositories.UserRepository;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }


}
