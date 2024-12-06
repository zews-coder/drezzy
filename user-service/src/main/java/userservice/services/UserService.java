package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userservice.entities.User;
import userservice.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
