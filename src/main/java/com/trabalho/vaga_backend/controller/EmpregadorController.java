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

import com.trabalho.vaga_backend.model.Empregador;
import com.trabalho.vaga_backend.repo.EmpregadorRepository;

import java.util.List;

@RestController
@RequestMapping("/empregadores")
@RequiredArgsConstructor
public class EmpregadorController {

    private final EmpregadorRepository empregadorRepository;

    @GetMapping
    public ResponseEntity<List<Empregador>> listarEmpregadores() {
        List<Empregador> empregadores = empregadorRepository.findAll();
        return ResponseEntity.ok(empregadores);
    }

    @PostMapping
    public ResponseEntity<Empregador> cadastrarEmpregador(@RequestBody Empregador empregador) {
        Empregador novoEmpregador = empregadorRepository.save(empregador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmpregador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empregador> atualizarEmpregador(@PathVariable Long id, @RequestBody Empregador empregadorAtualizado) {
        Empregador empregadorExistente = empregadorRepository.findById(id).orElse(null);
        if (empregadorExistente == null) {
            return ResponseEntity.notFound().build();
        }
        empregadorExistente.setNome(empregadorAtualizado.getNome());
        empregadorExistente.setEmpresa(empregadorAtualizado.getEmpresa());
        empregadorExistente.setEmail(empregadorAtualizado.getEmail());
        empregadorExistente.setTelefone(empregadorAtualizado.getTelefone());
        Empregador empregadorAtualizadoSalvo = empregadorRepository.save(empregadorExistente);
        return ResponseEntity.ok(empregadorAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEmpregador(@PathVariable Long id) {
        empregadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}