package com.vtr.exercises.service;

import com.vtr.exercises.dto.WorkoutDTO;
import com.vtr.exercises.mapper.WorkoutMapper;
import com.vtr.exercises.model.Workout;
import com.vtr.exercises.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    @Transactional
    public WorkoutDTO saveWorkout(WorkoutDTO workoutDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(workoutDTO)));
    }

    @Transactional(readOnly = true)
    public Page<WorkoutDTO> findAll(Pageable pageable) {
        Page<Workout> workouts = repository.findAll(pageable);
        return workouts.map(mapper::toDTO);
    }
}
