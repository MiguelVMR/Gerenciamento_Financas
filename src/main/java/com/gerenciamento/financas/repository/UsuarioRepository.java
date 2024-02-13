package com.gerenciamento.financas.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gerenciamento.financas.model.schema.UsuarioSchema;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioSchema, UUID> {

    UsuarioSchema findByDocumento(@Param("documento") String documento);

    @Query("""
        SELECT u FROM UsuarioSchema u
        WHERE
        (
            UPPER(u.documento) LIKE UPPER(CONCAT('%', :query, '%')) OR
            UPPER(u.email) LIKE UPPER(CONCAT('%', :query, '%')) OR
            UPPER(u.nome) LIKE UPPER(CONCAT('%', :query, '%'))
        )
    """)
    Page<UsuarioSchema> findAll(@Param("query") String query, Pageable pageable);

    boolean existsByEmail(@Param("email") String email);
    

}
