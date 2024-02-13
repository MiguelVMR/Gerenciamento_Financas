package com.gerenciamento.financas.model.schema;

import java.util.List;
import java.util.UUID;
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
@Table(name = "categoria")
public class CategoriaSchema extends GenericSchema{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String nome;

    @Column(name = "tipo_de_categoria",length = 50)
    private String tipoDeCategoria;

    @OneToMany(mappedBy = "categorias", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<TransacaoSchema> transacoes;
}
