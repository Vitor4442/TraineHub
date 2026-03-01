package com.vtr.exercises.controller;

import com.vtr.exercises.controller.docs.WorkoutControllerDocs;
import com.vtr.exercises.dto.WorkoutDTO;
import com.vtr.exercises.service.WorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/treinos")
@RequiredArgsConstructor
@Tag(name = "Treinos", description = "Endpoints para Gerenciar os treinos")
public class WorkoutController implements WorkoutControllerDocs {

    private final WorkoutService workoutService;
    private final PagedResourcesAssembler<WorkoutDTO> assembler;


    @PostMapping
    @Override
    public ResponseEntity<WorkoutDTO> addWorkout(@RequestBody WorkoutDTO workoutDTO){
        WorkoutDTO workout =  workoutService.saveWorkout(workoutDTO);
        return ResponseEntity.status(201).body(workout);
    }

    @GetMapping
    @Override
    public ResponseEntity<PagedModel<EntityModel<WorkoutDTO>>> findAllWorkouts (@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "12") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<WorkoutDTO> workout = workoutService.findAll(pageable);

        workout.forEach(this::addLinksToExercise);
        return ResponseEntity.ok(assembler.toModel(workout));
    }

    private void addLinksToExercise(WorkoutDTO workout) {
        workout.add(linkTo(methodOn(WorkoutController.class)
                .addWorkout(workout)).withRel("criar workout"));
    }

    @PutMapping ("/atualizar/{id}")
    @Override
    public ResponseEntity<WorkoutDTO> putWorkout (@PathVariable(value = "id") Long id, WorkoutDTO workoutDTO){
        WorkoutDTO workout = workoutService.attWorkout(id,workoutDTO);
        return ResponseEntity.status(201).body(workout);
    }
}
