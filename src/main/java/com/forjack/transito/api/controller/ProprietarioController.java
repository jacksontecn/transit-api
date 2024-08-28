package com.forjack.transito.api.controller;

import com.forjack.transito.domain.model.Proprietario;
import com.forjack.transito.domain.repository.ProprietarioRepository;
import com.forjack.transito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
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
    private final RegistroProprietarioService registroProprietarioService;

    @GetMapping
    public List<Proprietario> listar(){

        return proprietarioRepository.findAll();

    }
    @GetMapping("/listarPor")
    public List<Proprietario> listarPor(@RequestParam String nome){

        return proprietarioRepository.findByNomeContaining(nome);

    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){
        Optional<Proprietario> optional = proprietarioRepository.findById(proprietarioId);

        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario){
        return registroProprietarioService.salvar(proprietario);
      //  return proprietarioRepository.save(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                 @Valid @RequestBody Proprietario proprietario){
        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
            }
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){
        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(proprietarioId);
       // proprietarioRepository.deleteById(proprietarioId);
        return ResponseEntity.noContent().build();
    }


}


