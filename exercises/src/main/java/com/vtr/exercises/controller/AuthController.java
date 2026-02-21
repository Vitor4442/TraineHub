package com.vtr.exercises.controller;

import com.vtr.exercises.dto.security.AccountCredentialsDTO;
import com.vtr.exercises.dto.security.TokenDTO;
import com.vtr.exercises.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "End point para autenticação")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Authentificação do usuario e retorna o token")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO credentials){
        if(credentialsIsInvalid(credentials)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.singIn(credentials);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return ResponseEntity.ok().body(token);
    }

    private static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
        return credentials == null || StringUtils.isBlank(credentials.getPassword()) || StringUtils.isBlank(credentials.getEmail());
    }

    @Operation(summary = "Re authentifica o usuario e retorna o token")
    @PutMapping("/refresh/{email}")
    public ResponseEntity<?> refresh (@PathVariable String email, @RequestHeader("Authorization") String refreshToken){

        if(pamatersAreInvalid(email, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.refreshToken(email, refreshToken);

        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return ResponseEntity.ok().body(token);
    }

    private boolean pamatersAreInvalid(String email, String refreshToken) {
        return StringUtils.isBlank(email) || StringUtils.isBlank(refreshToken);
    }

    @Operation(summary = "Cria um usuario")
    @PostMapping("/createUser")
    public ResponseEntity<AccountCredentialsDTO> CreateUser (@RequestBody AccountCredentialsDTO user){
        return ResponseEntity.ok(authService.create(user));
    }


}
