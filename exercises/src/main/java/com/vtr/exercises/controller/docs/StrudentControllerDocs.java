package com.vtr.exercises.controller.docs;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface StrudentControllerDocs {

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
    ResponseEntity<StudentDTO> addStudent(@RequestBody Student student);

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
    ResponseEntity<CollectionModel<StudentDTO>> findAllStudents();

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
    ResponseEntity<StudentDTO> putStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id);

    @Operation(summary = "Deleted  Student", description = "Find student and Deleted", tags = {"Students"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    ResponseEntity<Void> deleteStudent(@PathVariable Long id);

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
    ResponseEntity<StudentDTO> findByIdStudent(@PathVariable Long id);

    @Operation(summary = "Disable Student", description = "Disabilita o estudante selecionado", tags = {"Students"}, responses = {
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
    ResponseEntity<StudentDTO> disable(@PathVariable Long id);
}
