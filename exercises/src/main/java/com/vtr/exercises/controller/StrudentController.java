package com.vtr.exercises.controller;

import com.vtr.exercises.controller.docs.StrudentControllerDocs;
import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import com.vtr.exercises.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Endpoints for manager students")
public class StrudentController implements StrudentControllerDocs {

    private final StudentService service;

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            value = "/cadastros" )
    @Override
    public ResponseEntity<StudentDTO> addStudent(@RequestBody Student student){
        StudentDTO savedStudent = service.addStudent(student);
        addLinksToExercise(savedStudent);
        return ResponseEntity.status(201).body(savedStudent);
    }


    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Override
    public ResponseEntity<CollectionModel<StudentDTO>> findAllStudents(){

        List<StudentDTO> student =  service.findAllStudents();
        for(StudentDTO students : student){
            addLinksToExercise(students);
        }

        CollectionModel<StudentDTO> collectionModel = CollectionModel.of(student);
        collectionModel.add(linkTo(methodOn(StrudentController.class).findAllStudents()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            value = "{id}"
    )
    @Override
    public ResponseEntity<StudentDTO> putStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        StudentDTO student = service.putStudent(id, studentDTO);
        addLinksToExercise(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            value = "{id}")
    @Override
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, value = "/{id}")
    @Override
    public ResponseEntity<StudentDTO> findByIdStudent(@PathVariable Long id){
        StudentDTO student = service.findByIdStudent(id);
        addLinksToExercise(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, value = "/imc/{id}")
    public ResponseEntity<StudentDTO> getIMCStudent(@PathVariable Long id){
        StudentDTO student = service.calcIMCByStudent(id);
    }

    private void addLinksToExercise(StudentDTO student) {
        student.add(linkTo(methodOn(StrudentController.class)
                .findByIdStudent(student.getId())).withSelfRel());

        student.add(linkTo(methodOn(StrudentController.class)
                .putStudent(student, student.getId())).withRel("update"));

        student.add(linkTo(methodOn(StrudentController.class)
                .deleteStudent(student.getId())).withRel("delete"));

        student.add(linkTo(methodOn(StrudentController.class)
                .findAllStudents()).withRel("all-exercises"));
    }
}
