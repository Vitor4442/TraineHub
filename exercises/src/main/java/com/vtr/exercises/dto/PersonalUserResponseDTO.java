package com.vtr.exercises.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import com.vtr.exercises.model.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PersonalUserResponseDTO {
    private Long id;
    private Long ownerId;
    private String name;
    private String email;
    private String phone;
    private Boolean active;
    private LocalDate birthDate;
    private Gender gender;
    private List<String> roles; // Descrições das permissões
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}