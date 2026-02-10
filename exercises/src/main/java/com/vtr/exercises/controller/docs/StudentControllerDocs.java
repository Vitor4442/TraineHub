package com.vtr.exercises.controller.docs;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface StudentControllerDocs {

    @Operation(
            summary = "Cadastrar aluno",
            description = "Realiza o cadastro de um novo aluno no sistema.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Aluno cadastrado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<StudentDTO> addStudent(@RequestBody Student student);

    @Operation(
            summary = "Listar alunos",
            description = "Retorna uma lista paginada de todos os alunos cadastrados.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de alunos retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class))
                            )
                    ),
                    @ApiResponse(responseCode = "204", description = "Nenhum aluno encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<StudentDTO>>> findAllStudents(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size
    );

    @Operation(
            summary = "Buscar alunos pelo nome",
            description = "Retorna uma lista paginada de alunos filtrados pelo nome.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Alunos encontrados com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class))
                            )
                    ),
                    @ApiResponse(responseCode = "204", description = "Nenhum aluno encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Parâmetro inválido", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<StudentDTO>>> findByName(
            @PathVariable("name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size
    );

    @Operation(
            summary = "Atualizar aluno",
            description = "Atualiza os dados de um aluno existente com base no ID informado.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Aluno atualizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<StudentDTO> putStudent(
            @RequestBody StudentDTO studentDTO,
            @PathVariable Long id
    );

    @Operation(
            summary = "Excluir aluno",
            description = "Remove um aluno do sistema com base no ID informado.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Aluno removido com sucesso", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<Void> deleteStudent(@PathVariable Long id);

    @Operation(
            summary = "Buscar aluno por ID",
            description = "Retorna os dados de um aluno específico com base no ID informado.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Aluno encontrado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<StudentDTO> findByIdStudent(@PathVariable Long id);

    @Operation(
            summary = "Desativar aluno",
            description = "Desativa um aluno no sistema, impedindo seu uso sem removê-lo da base de dados.",
            tags = {"Alunos"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Aluno desativado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
            }
    )
    ResponseEntity<StudentDTO> disable(@PathVariable Long id);
}
