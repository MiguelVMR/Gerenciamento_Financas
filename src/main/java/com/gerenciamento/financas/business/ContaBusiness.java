package com.gerenciamento.financas.business;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.gerenciamento.financas.exception.GerenciamentoException;
import com.gerenciamento.financas.gateway.ContaGateway;
import com.gerenciamento.financas.gateway.UsuarioGateway;
import com.gerenciamento.financas.model.dto.Contas;
import com.gerenciamento.financas.model.dto.Usuario;


@Component
public class ContaBusiness {
    
    private final ContaGateway contaGateway;
    private final UsuarioGateway usuarioGateway;

    @Autowired
    public ContaBusiness(ContaGateway contaGateway,UsuarioGateway usuarioGateway) {
        this.contaGateway = contaGateway;
        this.usuarioGateway  = usuarioGateway;
    }

    public Contas savarConta(Contas contas, UUID usuario_id){

     Usuario usuarioDb = usuarioGateway.findbyId(usuario_id);
     
     if(Objects.isNull(usuarioDb)){
        throw new GerenciamentoException("Não existe usuario com este id",
         HttpStatus.BAD_REQUEST);
     }

     contas.setUsuario(usuarioDb);

     if (contas.getSaldo() == null) {
        contas.setSaldo(BigDecimal.ZERO);
     }


        return contaGateway.save(contas);
    }

    public Page<Contas> findAll(String query, Pageable pageable) {
        return contaGateway.findAll(query, pageable);
    }

    public Contas findById(UUID id) {
        return contaGateway.findById(id);
    }

    public Contas update( Contas contas, UUID id) {

        Contas contaDb = contaGateway.findById(id);
      
        contas.setId(id);
       
        contas.setSaldo(contaDb.getSaldo());

        return contaGateway.save(contas);
    }
}
