package com.mini2.interest_service.jwt;


import com.mini2.interest_service.jwt.props.JwtConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@Component
public class JwtUtil {

    private static SecretKey key;

    private final JwtConfigProperties jwtProps;

    public JwtUtil(JwtConfigProperties jwtProps) {
        this.jwtProps = jwtProps;
    }

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtProps.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public static Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        return ((Number) claims.get("userId")).longValue();

    }


}
