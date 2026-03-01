package com.vtr.exercises.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDTO extends RepresentationModel<StudentDTO> {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private BigDecimal heightCm;
    private BigDecimal weightKg;
    private String goal;
    private String medicalNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean active;
}
