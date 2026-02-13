package com.vtr.exercises.controller;

import com.vtr.exercises.controller.docs.StudentControllerDocs;
import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import com.vtr.exercises.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Endpoints for manager students")
public class StudentController implements StudentControllerDocs {

    private final StudentService service;
    private final PagedResourcesAssembler<StudentDTO> assembler;

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            value = "/cadastros" )
    @Override
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student){
        StudentDTO savedStudent = service.addStudent(student);
        addLinksToExercise(savedStudent);
        return ResponseEntity.status(201).body(savedStudent);
    }

    
    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Override
    public ResponseEntity<PagedModel<EntityModel<StudentDTO>>> findAllStudents(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                               @RequestParam(value = "size", defaultValue = "12") Integer size,
                                                                               @RequestParam(value = "direction", defaultValue = "asc") String direction){
        var sortDirection =  "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        Page<StudentDTO> students = service.findAllStudents(pageable);
        students.forEach(this::addLinksToExercise);
        return ResponseEntity.ok(assembler.toModel(students));
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            value = "/nome/{name}"
    )
    @Override
    public ResponseEntity<PagedModel<EntityModel<StudentDTO>>> findByName(@PathVariable("name") String name, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "12") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentDTO> students = service.findByName(name, pageable);
        students.forEach(this::addLinksToExercise);
        return ResponseEntity.ok(assembler.toModel(students));
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


    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, value = "/desativar/{id}")
    @Override
    public ResponseEntity<StudentDTO> disable(@PathVariable Long id){
        StudentDTO studentDTO = service.disableStudent(id);
        addLinksToExercise(studentDTO);
       return ResponseEntity.ok(studentDTO);
    }

    private void addLinksToExercise(StudentDTO student) {
        student.add(linkTo(methodOn(StudentController.class)
                .findByIdStudent(student.getId())).withSelfRel());

        student.add(linkTo(methodOn(StudentController.class)
                .putStudent(student, student.getId())).withRel("update"));

        student.add(linkTo(methodOn(StudentController.class)
                .deleteStudent(student.getId())).withRel("delete"));

        student.add(linkTo(methodOn(StudentController.class)
                .findAllStudents(1, 12, "asc")).withRel("all-students"));
    }
}
