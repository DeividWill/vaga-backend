package com.trabalho.vaga_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trabalho.vaga_backend.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
}