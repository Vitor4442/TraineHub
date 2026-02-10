package com.vtr.exercises.dto;

import com.vtr.exercises.model.Exercises;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class WorkoutDTO extends RepresentationModel<WorkoutDTO> {
    private Long id;
    private ExerciseDTO exercises;
    private Integer sets;
    private String reps;
    private String advancedTecnique;
    private String restTime;
    private String notes;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
