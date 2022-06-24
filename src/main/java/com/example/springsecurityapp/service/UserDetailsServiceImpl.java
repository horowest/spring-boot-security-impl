package com.example.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurityapp.entity.AppUser;
import com.example.springsecurityapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AppUser user = userRepository.findByName(name);

        if(user == null) 
            throw new UsernameNotFoundException("user not found");

        return new UserDetailsImpl(user);
    }
    
}
