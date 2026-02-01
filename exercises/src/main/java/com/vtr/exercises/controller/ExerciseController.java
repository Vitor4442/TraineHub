package com.vtr.exercises.controller;

import com.vtr.exercises.controller.docs.ExerciseControllerDocs;
import com.vtr.exercises.dto.ExerciseDTO;
import com.vtr.exercises.service.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercicios")
@Tag(name = "Exercicios", description = "Endpoints para Gerenciar os Exercicios")
public class ExerciseController implements ExerciseControllerDocs {

    private final ExerciseService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Override
    public ResponseEntity<CollectionModel<ExerciseDTO>> findAllExercises() {
        List<ExerciseDTO> exercise = service.getAllExercises();

        for (ExerciseDTO exerciseDTO : exercise) {
            addLinksToExercise(exerciseDTO);
        }

        CollectionModel<ExerciseDTO> collectionModel = CollectionModel.of(exercise);
        collectionModel.add(linkTo(methodOn(ExerciseController.class).findAllExercises()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Override
    public ResponseEntity<ExerciseDTO> addExercise(@RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO savedExercise = service.AddExercise(exerciseDTO);
        addLinksToExercise(savedExercise);
        return ResponseEntity.status(201).body(savedExercise);
    }

    @PutMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ExerciseDTO> attExercise(@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO exercise = service.attExercise(id, exerciseDTO);
        addLinksToExercise(exercise);
        return ResponseEntity.ok(exercise);
    }

    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ExerciseDTO> deletedExercise(@PathVariable Long id) {
        service.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ExerciseDTO> findByIdExercise(@PathVariable Long id) {
        ExerciseDTO exercise = service.findById(id);
        addLinksToExercise(exercise);
        return ResponseEntity.ok(exercise);
    }

    private void addLinksToExercise(ExerciseDTO exercise) {
        exercise.add(linkTo(methodOn(ExerciseController.class)
                .findByIdExercise(exercise.getId())).withSelfRel());

        exercise.add(linkTo(methodOn(ExerciseController.class)
                .attExercise(exercise.getId(), exercise)).withRel("update"));

        exercise.add(linkTo(methodOn(ExerciseController.class)
                .deletedExercise(exercise.getId())).withRel("delete"));

        exercise.add(linkTo(methodOn(ExerciseController.class)
                .findAllExercises()).withRel("all-exercises"));
    }
}
