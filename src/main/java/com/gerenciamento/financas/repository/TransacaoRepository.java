package com.gerenciamento.financas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciamento.financas.model.schema.TransacaoSchema;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoSchema,UUID>{
    
}
