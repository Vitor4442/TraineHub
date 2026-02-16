package com.vtr.exercises.service;

import com.vtr.exercises.repository.PersonalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PersonalUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user =  repository.findByUserEmail(email);

        if(user != null) return user;
        else throw new UsernameNotFoundException("email" + email + "not foud!");
    }
}
