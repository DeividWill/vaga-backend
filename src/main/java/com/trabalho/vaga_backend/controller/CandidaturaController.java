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

import com.trabalho.vaga_backend.model.Candidatura;
import com.trabalho.vaga_backend.repo.CandidaturaRepository;

import java.util.List;

@RestController
@RequestMapping("/candidaturas")
@RequiredArgsConstructor
public class CandidaturaController {

    private final CandidaturaRepository candidaturaRepository;

    @GetMapping
    public ResponseEntity<List<Candidatura>> listarCandidaturas() {
        List<Candidatura> candidaturas = candidaturaRepository.findAll();
        return ResponseEntity.ok(candidaturas);
    }

    @PostMapping
    public ResponseEntity<Candidatura> cadastrarCandidatura(@RequestBody Candidatura candidatura) {
        Candidatura novaCandidatura = candidaturaRepository.save(candidatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCandidatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidatura> atualizarCandidatura(@PathVariable Long id, @RequestBody Candidatura candidaturaAtualizada) {
        Candidatura candidaturaExistente = candidaturaRepository.findById(id).orElse(null);
        if (candidaturaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        candidaturaExistente.setIdCandidato(candidaturaAtualizada.getIdCandidato());
        candidaturaExistente.setIdVaga(candidaturaAtualizada.getIdVaga());
        Candidatura candidaturaAtualizadaSalva = candidaturaRepository.save(candidaturaExistente);
        return ResponseEntity.ok(candidaturaAtualizadaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCandidatura(@PathVariable Long id) {
        candidaturaRepository.deleteById(id);
        return ResponseEntity.noContent().build();

}

}