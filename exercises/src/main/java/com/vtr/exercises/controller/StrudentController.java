package com.vtr.exercises.controller;

import com.vtr.exercises.controller.docs.StrudentControllerDocs;
import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import com.vtr.exercises.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.status(201).body(savedStudent);
    }


    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Override
    public ResponseEntity<List<StudentDTO>> findAllStudents(){
        return ResponseEntity.ok(service.findAllStudents());
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            value = "{id}"
    )
    @Override
    public ResponseEntity<StudentDTO> putStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        return ResponseEntity.ok( service.putStudent(id, studentDTO));
    }

    @DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            value = "{id}")
    @Override
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, value = "/{id}")
    @Override
    public ResponseEntity<StudentDTO> findByIdStudent(@PathVariable Long id){
        StudentDTO student = service.findByIdStudent(id);
        return ResponseEntity.ok(student);
    }

}
