package com.vtr.exercises.controller.docs;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.dto.WorkoutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface WorkoutControllerDocs {

    @Operation(
            summary = "Cadastrar Exercicio",
            description = "Realiza o cadastro de um novo exericicio no sistema.",
            tags = {"Treinos"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Treino cadastrado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WorkoutDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<WorkoutDTO> addWorkout(@RequestBody WorkoutDTO workoutDTO);

    @Operation(
            summary = "Vizuala todos os Treinos",
            description = "Vizualiza todos os Treinos do sistema.",
            tags = {"Treinos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista todos os Treinos com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WorkoutDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<WorkoutDTO>>> findAllWorkouts (@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "12") Integer size);

    @Operation(
            summary = "Atualiza o treino por ID",
            description = "Atualiza os dados de um treino específico com base no ID informado.",
            tags = {"Treinos"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Treino Atualizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WorkoutDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Treino não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<WorkoutDTO> putWorkout (@PathVariable(value = "id") Long id, WorkoutDTO workoutDTO);
}
