package com.esof.escolaesof.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.esof.escolaesof.exception.ContatoDeResponsavelNotFoundException;
import com.esof.escolaesof.exception.NomeDeResponsavelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.esof.escolaesof.exception.AlunoNotFoundException;
import com.esof.escolaesof.model.Aluno;
import com.esof.escolaesof.repository.AlunoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/escolaIdiomas/aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {

    private final AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosAlunos(){
        return ResponseEntity.ok( new ArrayList<>(alunoRepository.findAll()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno ) throws ContatoDeResponsavelNotFoundException, NomeDeResponsavelNotFoundException {
        if(aluno.getIdade() < 18 && aluno.getEmail_responsavel() == null || aluno.getIdade() < 18 && aluno.getTelefone_responsavel() == null ){
            throw new ContatoDeResponsavelNotFoundException();
        }
        if(aluno.getIdade() < 18 && aluno.getNome_responsavel() == null){
            throw  new NomeDeResponsavelNotFoundException();
        }

        Aluno novoAluno=  alunoRepository.save(aluno);

        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    @Transactional
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno alunoAtualizado, @PathVariable Long matricula) throws AlunoNotFoundException{
       Aluno aluno = verifyIfExists(matricula);
        aluno.setEmail_responsavel(alunoAtualizado.getEmail_responsavel());
        aluno.setTelefone_responsavel(alunoAtualizado.getTelefone_responsavel());
        aluno.setCurso(alunoAtualizado.getCurso());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    @Transactional
    public ResponseEntity<?> deleteExpense(@PathVariable Long matricula) throws AlunoNotFoundException {
        verifyIfExists(matricula);
        alunoRepository.deleteById(matricula);
        return ResponseEntity.ok().build();
    }

      private Aluno verifyIfExists(Long matricula) throws AlunoNotFoundException {
        return alunoRepository.findById(matricula).orElseThrow(()->new AlunoNotFoundException(matricula));
    }
    
}