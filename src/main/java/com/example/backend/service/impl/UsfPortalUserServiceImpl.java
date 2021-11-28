package com.example.backend.service.impl;

import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsfPortalUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UsfPortalUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email address" +  username + "not found"));

        if (!user.isActive()) {
            throw new UsernameNotFoundException("The user is not activate");
        }

        return mapToUserDetails(user);
    }

    private static UserDetails mapToUserDetails(User user) {

        List<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
