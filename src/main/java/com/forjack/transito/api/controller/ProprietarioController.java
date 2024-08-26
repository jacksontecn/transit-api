package com.forjack.transito.api.controller;

import com.forjack.transito.domain.model.Proprietario;
import com.forjack.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar(){

        return proprietarioRepository.findAll();

    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){
        Optional<Proprietario> optional = proprietarioRepository.findById(proprietarioId);

        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@RequestBody Proprietario proprietario){
        return proprietarioRepository.save(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                  @RequestBody Proprietario proprietario){
        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
            }
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){
        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        proprietarioRepository.deleteById(proprietarioId);
        return ResponseEntity.noContent().build();
    }


}


