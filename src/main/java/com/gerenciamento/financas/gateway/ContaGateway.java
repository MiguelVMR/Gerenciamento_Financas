package com.gerenciamento.financas.gateway;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.gerenciamento.financas.model.dto.Contas;
import com.gerenciamento.financas.model.schema.ContaSchema;
import com.gerenciamento.financas.repository.ContaRepository;
import com.gerenciamento.financas.utils.Mapper;

@Component
public class ContaGateway {

    private final Mapper mapper = new Mapper();
    private final ContaRepository contaRepository;

    @Autowired
    public ContaGateway(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Contas save(Contas contas) {
        return mapper.converter(contaRepository
                .save(mapper.converter(contas, ContaSchema.class)), Contas.class);
    }

    public Contas findById(UUID contas_id) {
        return mapper.converter(contaRepository.findById(contas_id), Contas.class);
    }

    public Page<Contas> findAll(String query, Pageable pageable) {
        return contaRepository.findAll(query, pageable)
                .map(c-> mapper.converter(c, Contas.class));
    }
}
