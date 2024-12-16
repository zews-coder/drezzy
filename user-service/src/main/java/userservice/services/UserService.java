package userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import userservice.entities.User;
import userservice.interfaces.MyDto;
import userservice.interfaces.MyEntity;
import userservice.interfaces.MyService;
import userservice.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements MyService {
    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public List<MyEntity> get() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User add(MyDto myDto) {
        return null;
//        return userRepository.save(user);
    }

    @Override
    public User update(MyDto myDto) {
        return null;
//        return userRepository.save(user);
    }

    @Override
    public void activate(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        user.setIsActive(true);
        userRepository.save(user);
    }

    @Override
    public void deactivate(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void empty() {

    }
}
