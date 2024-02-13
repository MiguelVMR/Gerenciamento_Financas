package com.gerenciamento.financas.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gerenciamento.financas.business.TransacoesBusiness;
import com.gerenciamento.financas.exception.GerenciamentoExceptionController;
import com.gerenciamento.financas.model.dto.Contas;
import com.gerenciamento.financas.model.dto.Transacoes;
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
@RequestMapping("transacoes")
@Tag(name = "Modulo de Transações")
public class TransacaoController {

    private final TransacoesBusiness transacoesBusiness;

    @Autowired
    public TransacaoController(TransacoesBusiness transacoesBusiness) {
        this.transacoesBusiness = transacoesBusiness;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema(implementation = GerenciamentoExceptionController.ErrorHandling.class)) }),
    })
    @Operation(summary = "Endpoint reponsável por cadastrar uma transação")
    @PostMapping()
    public ResponseEntity<Transacoes> savarConta(@RequestBody @Valid Transacoes transacoes,
            @RequestParam(name = "conta_id") final UUID conta_id) {
                Transacoes transacaoSalva = transacoesBusiness.savarTransacao(transacoes, conta_id);
        return ResponseEntity.status(HttpStatus.OK).body(transacaoSalva);
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
  @Operation(summary = "Endpoint reponsável por buscar todas as transações")
  @GetMapping("find-all")
  public ResponseEntity<Page<Transacoes>> findAllUsuarios(
      @RequestParam(value = "page", required = false) final Integer page,
      @RequestParam(value = "size", required = false) final Integer size,
      @RequestParam(value = "sorting", required = false) final String sorting,
      @Parameter(description = "Esta consulta filtra por: descrição, tipo de transação") @RequestParam(value = "query", required = false, defaultValue = "") final String query) {

    Page<Transacoes> response = transacoesBusiness.findAll(query, CustomPageable.getInstance(page, size, sorting));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
