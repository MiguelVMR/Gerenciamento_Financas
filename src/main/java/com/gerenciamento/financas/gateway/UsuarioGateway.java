package com.gerenciamento.financas.gateway;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.gerenciamento.financas.exception.GerenciamentoException;
import com.gerenciamento.financas.model.dto.Usuario;
import com.gerenciamento.financas.model.schema.UsuarioSchema;
import com.gerenciamento.financas.repository.UsuarioRepository;
import com.gerenciamento.financas.utils.Mapper;

@Component
public class UsuarioGateway {

    private final Mapper mapper = new Mapper();

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioGateway(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    

    public Usuario save(Usuario usuario){

        Usuario usuarioEncontrado = findByDocumento(usuario.getDocumento());

        if(Objects.nonNull(usuarioEncontrado)){
            throw new GerenciamentoException("Usuario já Existente", HttpStatus.BAD_REQUEST);
        }
        return mapper.converter(usuarioRepository
        .save(mapper.converter(usuario, UsuarioSchema.class)), Usuario.class);
    }

    public Usuario update(Usuario usuario){
        return mapper.converter(usuarioRepository
        .save(mapper.converter(usuario, UsuarioSchema.class)), Usuario.class);
    }


    private Usuario findByDocumento(String documento) {
        return mapper.converter(usuarioRepository.findByDocumento(documento), Usuario.class);

    }

    public Usuario findbyId(UUID id){
        return mapper.converter(usuarioRepository.findById(id), Usuario.class);
    }

    public Page<Usuario> findAll(String query, Pageable pageable) {
        return usuarioRepository.findAll(query,pageable)
        .map( u -> mapper.converter(u, Usuario.class));
        
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    

}
