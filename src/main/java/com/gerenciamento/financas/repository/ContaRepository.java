package com.gerenciamento.financas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerenciamento.financas.model.schema.ContaSchema;

@Repository
public interface ContaRepository extends JpaRepository<ContaSchema,UUID>{

     @Query("""
        SELECT c FROM ContaSchema c
        WHERE
        (
            UPPER(c.tipoConta) LIKE UPPER(CONCAT('%', :query, '%')) OR
            UPPER(c.nome) LIKE UPPER(CONCAT('%', :query, '%'))
        )
    """)
    Page<ContaSchema> findAll(String query, Pageable pageable);
    
}
