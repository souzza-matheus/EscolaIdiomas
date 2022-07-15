package com.esof.escolaesof.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.esof.escolaesof.model.Aluno;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    @Query("Select obj from Aluno obj where obj.curso.id =:id")
    List<Aluno> findByCursoId(Long id);
}
