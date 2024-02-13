package com.gerenciamento.financas.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.UUID;
import org.apache.commons.lang3.ObjectUtils;
import com.gerenciamento.financas.exception.GerenciamentoException;
import com.gerenciamento.financas.gateway.UsuarioGateway;
import com.gerenciamento.financas.model.dto.Usuario;
import com.gerenciamento.financas.utils.Validadors;

@Component
public class UsuarioBusiness {

    private final Validadors validadors = new Validadors();

    private final UsuarioGateway usuarioGateway;

    @Autowired
    public UsuarioBusiness(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario salvarUsuario(Usuario usuario) {

        String documento = usuario.getDocumento().replaceAll("[.\\-/]", "");

        if (documento.length() == 11) {
            validadors.validacaoCpf(usuario.getDocumento());
        }
        if (documento.length() == 14) {
            validadors.validacaoCnpj(usuario.getDocumento());
        }

        usuario.setDocumento(documento);

        Usuario usuarioSalvo = usuarioGateway.save(usuario);

        return usuarioSalvo;

    }

    public Page<Usuario> findAll(String query, Pageable pageable) {

        return usuarioGateway.findAll(query, pageable);
    }

    public void update(Usuario usuario, UUID id) {
        if (usuarioGateway.existsByEmail(usuario.getEmail())) {
            throw new GerenciamentoException("E-mail já cadastrado", HttpStatus.CONFLICT);
        }

        Usuario usuarioDb = usuarioGateway.findbyId(id);

        if (ObjectUtils.notEqual(usuarioDb.getDocumento(), usuario.getDocumento())) {
            throw new GerenciamentoException("O documento não pode ser alterado", HttpStatus.BAD_REQUEST);
        }

        usuario.setId(id);

        usuarioGateway.update(usuario);


    }

}
