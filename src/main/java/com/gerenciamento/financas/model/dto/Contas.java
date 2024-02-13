package com.gerenciamento.financas.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gerenciamento.financas.model.enums.EnumTipoConta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Contas {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Tipo de conta é obrigatório")
    private EnumTipoConta tipoConta;

    private BigDecimal saldo;

    @JsonIgnore
    private Usuario usuario;

    @JsonIgnore
    private List<Transacoes> transacoes;

}
