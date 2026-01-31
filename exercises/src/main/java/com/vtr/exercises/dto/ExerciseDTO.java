package com.vtr.exercises.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class ExerciseDTO extends RepresentationModel<ExerciseDTO> {
    private Long id;
    private String name;
    private String primaryMuscle;
    private String equipment;
    private String video_url;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
