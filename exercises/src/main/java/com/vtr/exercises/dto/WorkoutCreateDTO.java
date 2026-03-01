package com.vtr.exercises.dto;

public record WorkoutCreateDTO(
        Long exerciseId,
        Integer sets,
        String reps,
        String advancedTecnique,
        String restTime,
        String notes
) {}

