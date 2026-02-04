package com.vtr.exercises.controller;

import com.vtr.exercises.dto.WorkoutDTO;
import com.vtr.exercises.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treinos")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<WorkoutDTO> addWorkout(@RequestBody WorkoutDTO workoutDTO){
        WorkoutDTO workout =  workoutService.saveWorkout(workoutDTO);
        return ResponseEntity.status(201).body(workout);
    }
}
