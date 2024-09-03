package com.forjack.transito.domain.service;

import com.forjack.transito.domain.model.StatusVeiculo;
import com.forjack.transito.domain.model.Veiculo;
import com.forjack.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);

    }


}
