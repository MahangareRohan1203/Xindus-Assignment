package com.xindus.ecommerce.configuration;

import com.xindus.ecommerce.entities.Customers;
import com.xindus.ecommerce.exceptions.CustomerException;
import com.xindus.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customers existed = customerRepository.findByEmail(username).orElseThrow(() -> new CustomerException("User not found"));
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(existed.getRole()));
        return new User(existed.getEmail(), existed.getPassword(), list);
    }
}
