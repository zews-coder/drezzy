package userservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import userservice.entities.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    public Claims extractClaims(String token) {
        return (Claims) Jwts.parser().verifyWith(Jwts.SIG.HS256.key().build()).build().parse(token);
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

//    public boolean validateToken(String token, UserDetails user) {
//        return (user.getUsername().equals(extractEmail(token)) && !isTokenExpired(token));
//    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("Role", user.getRole());

        SecretKey key = Jwts.SIG.HS256.key().build();

        String jws = Jwts.builder()
                .subject(user.getEmail())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)
                .compact();
        return jws;
    }

}
