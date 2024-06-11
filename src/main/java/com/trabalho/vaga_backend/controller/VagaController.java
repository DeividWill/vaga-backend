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

import com.trabalho.vaga_backend.model.Vaga;
import com.trabalho.vaga_backend.repo.VagaRepository;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@RequiredArgsConstructor
public class VagaController {

    private final VagaRepository vagaRepository;

    @GetMapping
    public ResponseEntity<List<Vaga>> listarVagas() {
        List<Vaga> vagas = vagaRepository.findAll();
        return ResponseEntity.ok(vagas);
    }

    @PostMapping
    public ResponseEntity<Vaga> cadastrarVaga(@RequestBody Vaga vaga) {
        Vaga novaVaga = vagaRepository.save(vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVaga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        Vaga vagaExistente = vagaRepository.findById(id).orElse(null);
        if (vagaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        vagaExistente.setTitulo(vagaAtualizada.getTitulo());
        vagaExistente.setDescricao(vagaAtualizada.getDescricao());
        vagaExistente.setSalario(vagaAtualizada.getSalario());
        vagaExistente.setIdEmpregador(vagaAtualizada.getIdEmpregador());
        Vaga vagaAtualizadaSalva = vagaRepository.save(vagaExistente);
        return ResponseEntity.ok(vagaAtualizadaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerVaga(@PathVariable Long id) {
        vagaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}