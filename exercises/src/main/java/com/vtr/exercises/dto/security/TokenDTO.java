package com.vtr.exercises.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String acessToken;
    private String refreshToken;

    public TokenDTO(String email, boolean b, Date now, Date validity, String acessToken, String refreshToken) {
    }
}
