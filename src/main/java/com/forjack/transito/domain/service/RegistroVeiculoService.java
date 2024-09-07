package com.forjack.transito.domain.service;

import com.forjack.transito.domain.exception.NegocioException;
import com.forjack.transito.domain.model.Proprietario;
import com.forjack.transito.domain.model.StatusVeiculo;
import com.forjack.transito.domain.model.Veiculo;
import com.forjack.transito.domain.repository.ProprietarioRepository;
import com.forjack.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){
        if (novoVeiculo.getId() != null){
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um id!");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();

        if (placaEmUso){
            throw new NegocioException("Já existe um veículo cadastrado com esta placa.");
        }

       Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);

    }


}
