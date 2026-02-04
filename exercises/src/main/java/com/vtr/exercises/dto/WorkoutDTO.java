package com.vtr.exercises.dto;

import com.vtr.exercises.model.Exercises;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WorkoutDTO {
    private Long id;
    private Exercises exercises;
    private Integer sets;
    private String reps;
    private String advancedTecnique;
    private String restTime;
    private String notes;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
