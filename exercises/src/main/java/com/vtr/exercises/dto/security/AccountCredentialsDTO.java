package com.vtr.exercises.dto.security;

import com.vtr.exercises.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String phone;
    private LocalDate birthDate;
}
