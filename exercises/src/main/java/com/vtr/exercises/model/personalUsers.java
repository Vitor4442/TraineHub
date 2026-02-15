package com.vtr.exercises.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "personal_users")
public class personalUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @NotBlank(message = "Nome é obrigatorio")
    @Column(length = 150)
    private String name;

    @Email
    @NotBlank(message = "Email é obrigatorio")
    @Column(unique = true, length = 150)
    private String email;

    @NotBlank(message = "password é obrigatorio")
    @Column(nullable = false, length = 150)
    private String password;

    @Column(length = 20)
    private String phone;

    @NotBlank(message = "role obrigatoria")
    @Column( length = 30)
    private String role;

    @Builder.Default
    private Boolean active = true;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 20)
    private String gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}