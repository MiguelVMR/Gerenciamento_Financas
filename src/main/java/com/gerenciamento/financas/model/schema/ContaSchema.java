package com.gerenciamento.financas.model.schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.gerenciamento.financas.model.enums.EnumTipoConta;
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
@Table(name = "conta")
public class ContaSchema extends GenericSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta",length = 20)
    private EnumTipoConta tipoConta;

    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioSchema usuario;

    @OneToMany(mappedBy = "contas" ,cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<TransacaoSchema> transacoes;
}
