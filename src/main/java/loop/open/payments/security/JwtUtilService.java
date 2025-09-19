package loop.open.payments.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtUtilService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    public Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.getExpiration() : null;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);
        if (claims == null) return false;
        String username = claims.getSubject();
        Date exp = claims.getExpiration();
        return username != null
                && username.equals(userDetails.getUsername())
                && exp != null
                && exp.after(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1000L * 60 * 60 * 10)) // 10h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // --- privado
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
