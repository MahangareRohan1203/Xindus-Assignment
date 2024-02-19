package com.xindus.ecommerce.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtProvider extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts
                    .builder()
                    .claim("username", authentication.getName())
                    .claim("authorities",populate(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 30000000))
                    .signWith(secretKey)
                    .compact();
            response.setHeader(JwtConstants.JWT_Header, "Bearer "+jwt);
        }
        filterChain.doFilter(request, response);
    }

    private String populate(Collection<? extends GrantedAuthority> list) {
        Set<String> set = new HashSet<>();
        for (GrantedAuthority g : list) {
            set.add(g.getAuthority());
        }
        return String.join(",", set);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().contains("/signIn");
    }
}
