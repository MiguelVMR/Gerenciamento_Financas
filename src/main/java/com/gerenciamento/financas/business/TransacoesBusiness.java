package com.gerenciamento.financas.business;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.gerenciamento.financas.exception.GerenciamentoException;
import com.gerenciamento.financas.gateway.ContaGateway;
import com.gerenciamento.financas.gateway.TransacoesGateway;
import com.gerenciamento.financas.model.dto.Contas;
import com.gerenciamento.financas.model.dto.Transacoes;

@Component
public class TransacoesBusiness {

    private final TransacoesGateway transacoesGateway;

    private final ContaGateway contaGateway;

    @Autowired
    public TransacoesBusiness(TransacoesGateway transacoesGateway, ContaGateway contaGateway) {
        this.transacoesGateway = transacoesGateway;
        this.contaGateway = contaGateway;
    }

    public Transacoes savarTransacao(Transacoes transacoes, UUID conta_id) {

        Contas contaDb = contaGateway.findById(conta_id);

        if (Objects.isNull(contaDb)) {
            throw new GerenciamentoException("Não existe conta com este id",
                    HttpStatus.BAD_REQUEST);
        }

        transacoes.setContas(contaDb);

        return transacoesGateway.saveTransacoes(transacoes);

    }

    public Page<Transacoes> findAll(String query, Pageable pageable) {
        return transacoesGateway.findAll(query, pageable);
    }
}
