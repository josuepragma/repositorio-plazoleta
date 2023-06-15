package com.pragma.smallsquare.restaurant.insfrastructure.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    public UserDetails loadUserByUsername(String username, Claims claims) throws UsernameNotFoundException {
        return User.builder()
                .username(username)
                .password("foo")
                .roles(claims.get("role").toString())
                .build();
    }
}
