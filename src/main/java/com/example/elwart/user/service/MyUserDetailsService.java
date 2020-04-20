package com.example.elwart.user.service;

import com.example.elwart.user.model.Role;
import com.example.elwart.user.model.User;
import com.example.elwart.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        try {
            User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("No user found with email: " + email));
            return new org.springframework.security.core.userdetails.User
                    (user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles)
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return authorities;
    }
}

