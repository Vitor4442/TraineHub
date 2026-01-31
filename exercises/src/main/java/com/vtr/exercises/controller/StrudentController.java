package com.vtr.exercises.controller;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import com.vtr.exercises.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class StrudentController {

    private final StudentService service;

    @PostMapping("/cadastros")
    public ResponseEntity<StudentDTO> addStudent (@RequestBody Student student){
        StudentDTO savedStudent = service.addStudent(student);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAllStudents (){
        return ResponseEntity.ok(service.findAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> putStudent (@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        return ResponseEntity.ok( service.putStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findByIdStudent(@PathVariable Long id){
        StudentDTO student = service.findByIdStudent(id);
        return ResponseEntity.ok(student);
    }

}
