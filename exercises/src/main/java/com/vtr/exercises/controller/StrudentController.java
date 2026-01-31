package com.vtr.exercises.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Endpoints for manager students")
public class StrudentController {

    private final StudentService service;

    @PostMapping("/cadastros")
    @Operation(summary = "Add Student", description = "Cadastra o aluno", tags = {"Students"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    public ResponseEntity<StudentDTO> addStudent (@RequestBody Student student){
        StudentDTO savedStudent = service.addStudent(student);
        return ResponseEntity.status(201).body(savedStudent);
    }


    @GetMapping
    @Operation(summary = "Find All Students", description = "Find all Students", tags = {"Students"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class))
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    public ResponseEntity<List<StudentDTO>> findAllStudents (){
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Put Student", description = "Atualiza o estudante", tags = {"Students"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> putStudent (@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        return ResponseEntity.ok( service.putStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted  Student", description = "Find student and Deleted", tags = {"Students"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Student", description = "Find a Specif person by your ID", tags = {"Students"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    public ResponseEntity<StudentDTO> findByIdStudent(@PathVariable Long id){
        StudentDTO student = service.findByIdStudent(id);
        return ResponseEntity.ok(student);
    }

}
