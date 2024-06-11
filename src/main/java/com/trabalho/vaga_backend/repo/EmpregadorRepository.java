package com.trabalho.vaga_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.vaga_backend.model.Empregador;

@Repository
public interface EmpregadorRepository extends JpaRepository<Empregador, Long> {
}