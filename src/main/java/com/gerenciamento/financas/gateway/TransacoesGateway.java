package com.gerenciamento.financas.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gerenciamento.financas.model.dto.Contas;
import com.gerenciamento.financas.model.dto.Transacoes;
import com.gerenciamento.financas.model.schema.TransacaoSchema;
import com.gerenciamento.financas.repository.TransacaoRepository;
import com.gerenciamento.financas.utils.Mapper;

@Component
public class TransacoesGateway {
    private final Mapper mapper = new Mapper();

    private final TransacaoRepository transacaoRepository;

    @Autowired
    public TransacoesGateway(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacoes saveTransacoes(Transacoes transacoes) {
        return mapper.converter(transacaoRepository
                .save(mapper.converter(transacoes, TransacaoSchema.class)), Transacoes.class);
    }

    public Page<Transacoes> findAll(String query, Pageable pageable) {
        return transacaoRepository.findAll(query, pageable)
                .map(t -> mapper.converter(t, Transacoes.class));
    }

}
