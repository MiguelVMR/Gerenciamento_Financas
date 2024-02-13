package com.gerenciamento.financas.model.schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gerenciamento.financas.model.enums.EnumTipoCategoria;
import com.gerenciamento.financas.model.enums.EnumTipoTransacao;
import com.gerenciamento.financas.model.utils.GenericSchema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacoes")
public class TransacaoSchema extends GenericSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String descricao;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao",length = 15)
    private EnumTipoTransacao tipoTransacao;

    private LocalDateTime dataTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaSchema contas;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_transacao",length = 50)
    private EnumTipoCategoria categoriaTransacao;
    
}
