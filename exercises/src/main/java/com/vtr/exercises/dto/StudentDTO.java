package com.vtr.exercises.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDTO {
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
}
