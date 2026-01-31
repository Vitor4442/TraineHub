package com.vtr.exercises.controller;

import com.vtr.exercises.dto.ExerciseDTO;
import com.vtr.exercises.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercicios")
public class ExerciseController {
    private final ExerciseService service;

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> findAllExercises(){
        return ResponseEntity.ok( service.getAllExercises());
    }

    @PostMapping
    public ResponseEntity<ExerciseDTO> addExercise(@RequestBody ExerciseDTO exerciseDTO){
        ExerciseDTO  savedExercise = service.AddExercise(exerciseDTO);
        return ResponseEntity.status(201).body(savedExercise);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ExerciseDTO>  attExercise (@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO){
       return ResponseEntity.ok(service.attExercise(id, exerciseDTO));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletedExercise (@PathVariable Long id){
        service.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ExerciseDTO> findByIdExercise (@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
