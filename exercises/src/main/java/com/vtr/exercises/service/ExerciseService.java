package com.vtr.exercises.service;

import com.vtr.exercises.dto.ExerciseDTO;
import com.vtr.exercises.mapper.ExerciseMapper;
import com.vtr.exercises.model.Exercises;
import com.vtr.exercises.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseMapper mapper;
    private final ExerciseRepository repository;

    @Transactional(readOnly = true)
    public List<ExerciseDTO> getAllExercises (){
      return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional
    public ExerciseDTO AddExercise(ExerciseDTO exerciseDTO){
        return mapper.toDTO(repository.save(mapper.toEntity(exerciseDTO))) ;
    }

    @Transactional
    public ExerciseDTO attExercise(Long id, ExerciseDTO exerciseDTO) {
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        mapper.UpdatedExerciseEntityForDTO(exerciseDTO, exercises);
        return mapper.toDTO(repository.save(exercises));
    }

    public void deleteExercise(Long id) {
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        repository.delete(exercises);
    }

    public ExerciseDTO findById(Long id) {
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        return mapper.toDTO(exercises);
    }
}
