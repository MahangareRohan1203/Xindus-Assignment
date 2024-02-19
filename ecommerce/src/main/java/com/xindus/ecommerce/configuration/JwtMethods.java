package com.xindus.ecommerce.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtMethods {

    public String getEmailFromToken(String jwt){
        SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();
        return String.valueOf(claims.get("username"));
    }
}
