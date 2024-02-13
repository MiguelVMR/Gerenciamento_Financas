package com.gerenciamento.financas.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gerenciamento.financas.model.enums.EnumTipoCategoria;
import com.gerenciamento.financas.model.enums.EnumTipoTransacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Transacoes {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Descricao é obrigatório")
    @NotEmpty(message = "Descricao é obrigatório")
    private String descricao;

    @NotNull(message = "Valor da transação não pode ser nulo")
    @Positive(message = "O valor da transação tem que ser um valor positivo")
    private BigDecimal valor;

    @NotNull(message = "Tipo de transação é obrigatório")
    private EnumTipoTransacao tipoTransacao;

    private LocalDateTime dataTransacao = LocalDateTime.now();

    @JsonIgnore
    private Contas contas;

    @NotNull(message = "Tipo de transação é obrigatório")
    private EnumTipoCategoria categoriaTransacao;

}
