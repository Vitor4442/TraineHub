package com.vtr.exercises.service;

import com.vtr.exercises.dto.WorkoutDTO;
import com.vtr.exercises.mapper.ExerciseMapper;
import com.vtr.exercises.mapper.WorkoutMapper;
import com.vtr.exercises.model.Exercises;
import com.vtr.exercises.model.Workout;
import com.vtr.exercises.repository.ExerciseRepository;
import com.vtr.exercises.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    public WorkoutDTO saveWorkout(WorkoutDTO workoutDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(workoutDTO)));
    }
}
