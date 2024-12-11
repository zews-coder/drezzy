package userservice.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter  {
    private final JwtUtils jwtUtils;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String jwt = null;
//        String username = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwt = authHeader.substring(7);
//            username = jwtUtils.extractEmail(jwt);
//        }
//    }
}
