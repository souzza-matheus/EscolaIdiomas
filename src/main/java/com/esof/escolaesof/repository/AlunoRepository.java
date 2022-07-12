package com.esof.escolaesof.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.esof.escolaesof.model.Aluno;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    List<Aluno> findByCursoId(Long id);
}
