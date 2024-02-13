package com.gerenciamento.financas.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerenciamento.financas.model.schema.TransacaoSchema;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoSchema,UUID>{

     @Query("""
        SELECT t FROM TransacaoSchema t
        WHERE
        (
            UPPER(t.tipoTransacao) LIKE UPPER(CONCAT('%', :query, '%')) OR
            UPPER(t.descricao) LIKE UPPER(CONCAT('%', :query, '%'))
        )
    """)
    Page<TransacaoSchema> findAll(String query, Pageable pageable);
    
}
