package com.forjack.transito.domain.service;

import com.forjack.transito.domain.exception.NegocioException;
import com.forjack.transito.domain.model.Proprietario;
import com.forjack.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public Proprietario buscar(Long proprietarioId){
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(() -> new NegocioException("Proprietário não encontrado!"));
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario){

        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario)).isPresent();

        if (emailEmUso){
            throw new NegocioException("E-mail já cadastrado.");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId){
        proprietarioRepository.deleteById(proprietarioId);
    }

}
