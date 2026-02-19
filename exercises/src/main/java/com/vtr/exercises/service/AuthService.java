package com.vtr.exercises.service;

import com.vtr.exercises.dto.security.AccountCredentialsDTO;
import com.vtr.exercises.dto.security.TokenDTO;
import com.vtr.exercises.repository.PersonalUserRepository;
import com.vtr.exercises.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PersonalUserRepository repository;

    public ResponseEntity<TokenDTO> singIn(AccountCredentialsDTO credentials){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword()
            )
        );

        var user = repository.findByUserEmail(credentials.getEmail());
        if(user == null) throw new UsernameNotFoundException("Email " + credentials.getEmail() + " not found");

        var tokenResponse = tokenProvider.createAcessToken(
                credentials.getEmail(),
                user.getRoles()
        );

        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken){
        var user = repository.findByUserEmail(username);
        TokenDTO token;
        if(user == null) throw new UsernameNotFoundException("Email " + username + " not found");
        else token = tokenProvider.refreshToken(refreshToken);
        return ResponseEntity.ok(token);
    }
}
