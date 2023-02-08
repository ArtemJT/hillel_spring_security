package com.example.thmlfdemo.security;

import com.example.thmlfdemo.model.User;
import com.example.thmlfdemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vladimir Bratchikov on 04.02.23
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User byEmail = userRepository.findFirstByEmail(username).orElseThrow(() -> new EntityNotFoundException());

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(byEmail.getUserRole().name()));

        return new org.springframework.security.core.userdetails.User(byEmail.getEmail(), byEmail.getPassword(), roles);
    }
}
