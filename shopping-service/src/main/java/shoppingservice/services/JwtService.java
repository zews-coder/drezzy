package shoppingservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class JwtService {
    private final String secretKey = "sBQ4tLJ7GswrYcT47TXlRoCl7WZmTL4No8YsX2aRf44bb6NpKwAdVAXcFHQTT0NN";

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractId(String token) {
        Map<String, Object> claims = extractAllClaims(token);
        return Long.parseLong(claims.get("id").toString());
    }

    public String extractRole(String token) {
        Map<String, Object> claims = extractAllClaims(token);
        return claims.get("role").toString();
    }

    public boolean isAdmin(String token) {
        Map<String, Object> claims = extractAllClaims(token);
        return claims.get("role").toString().equalsIgnoreCase("admin");
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        return extractSubject(jwtToken).equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }
}
