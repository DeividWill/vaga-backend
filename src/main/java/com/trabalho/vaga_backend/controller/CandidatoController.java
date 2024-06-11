package com.trabalho.vaga_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.vaga_backend.model.Candidato;
import com.trabalho.vaga_backend.repo.CandidatoRepository;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoRepository candidatoRepository;

    @GetMapping
    public ResponseEntity<List<Candidato>> listarCandidatos() {
        List<Candidato> candidatos = candidatoRepository.findAll();
        return ResponseEntity.ok(candidatos);
    }

    @PostMapping
    public ResponseEntity<Candidato> cadastrarCandidato(@RequestBody Candidato candidato) {
        Candidato novoCandidato = candidatoRepository.save(candidato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCandidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> atualizarCandidato(@PathVariable Long id, @RequestBody Candidato candidatoAtualizado) {
        Candidato candidatoExistente = candidatoRepository.findById(id).orElse(null);
        if (candidatoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        candidatoExistente.setNome(candidatoAtualizado.getNome());
        candidatoExistente.setEmail(candidatoAtualizado.getEmail());
        candidatoExistente.setTelefone(candidatoAtualizado.getTelefone());
        candidatoExistente.setCurriculo(candidatoAtualizado.getCurriculo());
        Candidato candidatoAtualizadoSalvo = candidatoRepository.save(candidatoExistente);
        return ResponseEntity.ok(candidatoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCandidato(@PathVariable Long id) {
        candidatoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}