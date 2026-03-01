package com.vtr.exercises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaDTO {
    private Long id;
    private Long studentId;
    private String nome;
    private String descricao;
    private Timestamp created_at;
    private Timestamp updated_at;
}
