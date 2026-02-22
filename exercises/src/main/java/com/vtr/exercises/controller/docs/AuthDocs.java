package com.vtr.exercises.controller.docs;

import com.vtr.exercises.dto.security.AccountCredentialsDTO;
import com.vtr.exercises.dto.security.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Auth", description = "Endpoints de autenticação e criação de usuário")
public interface AuthDocs {

    @Operation(
            summary = "Login (signin)",
            description = "Autentica o usuário com email e senha e retorna os tokens (access + refresh)."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Autenticado com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenDTO.class),
                    examples = @ExampleObject(
                            name = "TokenDTO exemplo",
                            value = """
                                    {
                                      "email": "jose@email.com",
                                      "authenticated": true,
                                      "created": "2026-02-21T12:00:00.000-03:00",
                                      "expiration": "2026-02-21T13:00:00.000-03:00",
                                      "acessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                                      "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                                    }
                                    """
                    )
            )
    )
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden - credenciais inválidas", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    ResponseEntity<?> signin(
            @RequestBody(
                    required = true,
                    description = "Credenciais do usuário",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountCredentialsDTO.class),
                            examples = @ExampleObject(
                                    name = "Credentials exemplo",
                                    value = """
                                            {
                                              "email": "jose@email.com",
                                              "password": "SenhaForte123"
                                            }
                                            """
                            )
                    )
            )
            com.vtr.exercises.dto.security.AccountCredentialsDTO credentials
    );

    @Operation(
            summary = "Refresh do token",
            description = "Reautentica usando o refresh token e retorna um novo access token (e opcionalmente um novo refresh).",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Token renovado com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenDTO.class)
            )
    )
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden - refresh inválido", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    ResponseEntity<?> refresh(
            @Parameter(
                    name = "email",
                    description = "Email do usuário que terá o token renovado",
                    required = true,
                    in = ParameterIn.PATH,
                    example = "jose@email.com"
            )
            @PathVariable String email,

            @Parameter(
                    name = "Authorization",
                    description = "Refresh token no header Authorization. Ex: Bearer <refresh_token>",
                    required = true,
                    in = ParameterIn.HEADER,
                    example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
            )
            @RequestHeader("Authorization") String refreshToken
    );

    @Operation(
            summary = "Criar usuário",
            description = "Cria um novo usuário no sistema. (Se você também quiser já retornar TokenDTO, dá pra ajustar)."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário criado com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AccountCredentialsDTO.class)
            )
    )
    @ApiResponse(responseCode = "400", description = "Bad Request - dados inválidos", content = @Content)
    @ApiResponse(responseCode = "409", description = "Conflict - email já existe", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    ResponseEntity<AccountCredentialsDTO> createUser(
            @RequestBody(
                    required = true,
                    description = "Dados do usuário a ser criado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountCredentialsDTO.class),
                            examples = @ExampleObject(
                                    name = "CreateUser exemplo",
                                    value = """
                                            {
                                              "name": "José Vitor",
                                              "email": "jose@email.com",
                                              "password": "SenhaForte123",
                                              "gender": "MASCULINO",
                                              "phone": "12998887766",
                                              "birthDate": "2005-03-15"
                                            }
                                            """
                            )
                    )
            )
            AccountCredentialsDTO user
    );
}