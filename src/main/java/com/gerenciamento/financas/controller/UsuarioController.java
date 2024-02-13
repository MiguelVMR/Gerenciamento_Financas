package com.gerenciamento.financas.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gerenciamento.financas.business.UsuarioBusiness;
import com.gerenciamento.financas.exception.GerenciamentoExceptionController;
import com.gerenciamento.financas.model.dto.Usuario;
import com.gerenciamento.financas.utils.CustomPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
@Tag(name = "Modulo de Usuarios")
public class UsuarioController {

  private final UsuarioBusiness usuarioBusiness;

  @Autowired
  public UsuarioController(UsuarioBusiness usuarioBusiness) {
    this.usuarioBusiness = usuarioBusiness;
  }

  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
  })
  @Operation(summary = "Endpoint reponsável por cadastrar um usuário")
  @PostMapping()
  public ResponseEntity<Usuario> salvarUsuario(@RequestBody @Valid Usuario usuario) {
    usuarioBusiness.salvarUsuario(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
  }

  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
  })
  @Operation(summary = "Endpoint reponsável por buscar todos os Usuários")
  @GetMapping("find-all")
  public ResponseEntity<Page<Usuario>> findAllUsuarios(
      @RequestParam(value = "page", required = false) final Integer page,
      @RequestParam(value = "size", required = false) final Integer size,
      @RequestParam(value = "sorting", required = false) final String sorting,
      @Parameter(description = "Esta consulta filtra por: documento, nome ou email") @RequestParam(value = "query", required = false, defaultValue = "") final String query) {

    Page<Usuario> response = usuarioBusiness.findAll(query, CustomPageable.getInstance(page, size, sorting));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
  })
  @Operation(summary = "Endpoint reponsável por autalizar um usuário")
  @PutMapping("update")
  public ResponseEntity<Void> update(@RequestBody @Valid Usuario usuario,
  @RequestParam(value = "id",required = true) final UUID id){

    usuarioBusiness.update(usuario,id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }



}
