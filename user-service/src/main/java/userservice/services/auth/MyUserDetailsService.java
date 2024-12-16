package userservice.services.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import userservice.entities.User;
import userservice.services.UserService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with the email: " + email + " not found");
        }
        if (!user.getIsActive()){
            throw new UsernameNotFoundException("User with the email: " + email + " is not active");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
