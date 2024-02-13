package com.gerenciamento.financas.model.schema;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.financas.model.enums.EnumTipoUsuario;
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
@Table(name = "usuarios")
public class UsuarioSchema extends GenericSchema{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 14)
    private String documento;

    @Column(length = 50)
    private String nome;

    @Column(length = 60)
    private String email;

    @Column(length = 60)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario",length = 20)
    private EnumTipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<ContaSchema> contas;
}
