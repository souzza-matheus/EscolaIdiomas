package com.esof.escolaesof.controller;

import java.sql.SQLException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esof.escolaesof.model.Professor;
import com.esof.escolaesof.repository.ProfessorRepository;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository _professorRepository;
	
	@GetMapping
	public List<Professor> Listar(){
		
		return _professorRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Professor _professor) throws SQLException {	
	
		try {			
			_professorRepository.save(_professor);
			return "Cadastro realizado com sucesso!";
			
		} catch (Exception e) {			
			return "Falha ao realizar o cadastro";
		}	
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable long id){
	   return _professorRepository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Professor professor) {
	   return _professorRepository.findById(id)
	           .map(Professor -> {
	               Professor.setNome(professor.getNome());
	               Professor.setSobrenome(professor.getSobrenome());
	               Professor.setIdioma(professor.getIdioma());
	               Professor.setTurno(professor.getTurno());
	               Professor.setEmail(professor.getEmail());
	               Professor.setTelefone(professor.getTelefone());
	               Professor updated = _professorRepository.save(professor);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return _professorRepository.findById(id)
	           .map(Professor -> {
	               _professorRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	

}
