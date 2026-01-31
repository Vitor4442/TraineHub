package com.vtr.exercises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {
    private String name;
    private String primaryMuscle;
    private String equipment;
    private String video_url;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
