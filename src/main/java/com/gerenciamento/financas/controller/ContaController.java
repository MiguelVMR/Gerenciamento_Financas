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
import com.gerenciamento.financas.business.ContaBusiness;
import com.gerenciamento.financas.exception.GerenciamentoExceptionController;
import com.gerenciamento.financas.model.dto.Contas;
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
@RequestMapping("contas")
@Tag(name = "Modulo de Contas")
public class ContaController {

  private final ContaBusiness contaBusiness;

  @Autowired
  public ContaController(ContaBusiness contaBusiness) {
    this.contaBusiness = contaBusiness;
  }

  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
          @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
  })
  @Operation(summary = "Endpoint reponsável por cadastrar uma conta")
  @PostMapping()
  public ResponseEntity<Contas> savarConta(@RequestBody @Valid Contas contas,
      @RequestParam(name = "usuario_id") final UUID usuario_id) {
    Contas savarConta = contaBusiness.savarConta(contas, usuario_id);
    return ResponseEntity.status(HttpStatus.OK).body(savarConta);
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
  @Operation(summary = "Endpoint reponsável por buscar todas as Contas")
  @GetMapping("find-all")
  public ResponseEntity<Page<Contas>> findAllUsuarios(
      @RequestParam(value = "page", required = false) final Integer page,
      @RequestParam(value = "size", required = false) final Integer size,
      @RequestParam(value = "sorting", required = false) final String sorting,
      @Parameter(description = "Esta consulta filtra por: id, nome ou tipo de conta") @RequestParam(value = "query", required = false, defaultValue = "") final String query) {

    Page<Contas> response = contaBusiness.findAll(query, CustomPageable.getInstance(page, size, sorting));
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
  @Operation(summary = "Endpoint reponsável por buscar a Conta pelo id")
  @GetMapping("find-by-id")
  public ResponseEntity<Contas>findById(
      @RequestParam(value = "id", required = false) final UUID id
     ) {

      Contas response = contaBusiness.findById(id);
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
  @Operation(summary = "Endpoint reponsável por atualizar uma Conta")
  @PutMapping("update")
  public ResponseEntity<Contas>update(
      @RequestParam(value = "id", required = false) final UUID id,
      @RequestBody @Valid final Contas contas
     ) {

      Contas response = contaBusiness.update(contas,id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }



}
