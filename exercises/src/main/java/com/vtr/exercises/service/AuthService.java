package com.vtr.exercises.service;

import com.vtr.exercises.dto.security.AccountCredentialsDTO;
import com.vtr.exercises.dto.security.TokenDTO;
import com.vtr.exercises.mapper.PersonalUserMapper;
import com.vtr.exercises.model.Permission;
import com.vtr.exercises.model.PersonalUsers;
import com.vtr.exercises.repository.PermissionRepository;
import com.vtr.exercises.repository.PersonalUserRepository;
import com.vtr.exercises.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonalUserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PersonalUserRepository repository;
    private final PermissionRepository permissionRepository;

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

    public AccountCredentialsDTO create (AccountCredentialsDTO user){
        System.out.println("DTO recebido: " + user);
         PersonalUsers personalUsers =  mapper.toEntity(user);
        System.out.println("Entity mapeada: name=" + personalUsers.getName()
                + ", email=" + personalUsers.getEmail()
                + ", phone=" + personalUsers.getPhone()
                + ", birthDate=" + personalUsers.getBirthDate()
                + ", gender=" + personalUsers.getGender());
         personalUsers.setPassword(generateHashedPassword( user.getPassword()));
        Permission p2 = permissionRepository.findById(2L).orElseThrow(() -> new IllegalStateException("Permission 2 não existe no banco"));
        personalUsers.setPermissions(List.of(p2));
         return  mapper.toDTO(repository.save(personalUsers));
    }

    private String generateHashedPassword(String pasword) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
         passwordEncoder.encode("admin1234");
         return passwordEncoder.encode(pasword);
    }
}
