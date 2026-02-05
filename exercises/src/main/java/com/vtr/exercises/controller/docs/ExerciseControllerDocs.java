package com.vtr.exercises.controller.docs;

import com.vtr.exercises.dto.ExerciseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


public interface ExerciseControllerDocs {
    @Operation(summary = "Adicionar Exercisio", description = "Essa requisição ira adicionar exercicios", tags = {"Exercicios"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    ResponseEntity<ExerciseDTO> addExercise(@RequestBody ExerciseDTO exerciseDTO);

    @Operation(summary = "Listar todos os Exercicios", description = "Essa requisição ira  Listar todos os exercicios", tags = {"Exercicios"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    ResponseEntity<Page<ExerciseDTO>> findAllExercises(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                         @RequestParam(value = "size", defaultValue = "12") Integer size);

    @Operation(summary = "Atualizar e Exercicio", description = "Essa requisição ira Atualizar o exercicio selecionado", tags = {"Exercicios"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    ResponseEntity<ExerciseDTO> attExercise(@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO);

    @Operation(summary = "Deleta o Exercicio", description = "Essa requisição ira deletar o exercicio selecionado", tags = {"Exercicios"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    ResponseEntity<ExerciseDTO> deletedExercise(@PathVariable Long id);

    @Operation(summary = "Seleciona o Exercicio", description = "Essa requisição ira selecionar o exercicio desejado", tags = {"Exercicios"}, responses = {
            @ApiResponse(description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseDTO.class)
                    )),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content)
    })
    ResponseEntity<ExerciseDTO> findByIdExercise(@PathVariable Long id);
}
