package com.esof.escolaesof.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
@RequestMapping("api/v1/escolaIdiomas/professor")
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

		ArrayList<String> _validationMsgs = new ArrayList<String>();
		
		if(validarProfessor(_professor, _validationMsgs)){
			
			try {			
				_professorRepository.save(_professor);
				return "Cadastro realizado com sucesso!";
				
			} catch (Exception e) {			
				return "Falha ao realizar o cadastro";
			}	
		}
		else{
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
	               Professor.setCurso(professor.getCurso());
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

	public boolean validarProfessor(Professor professor, ArrayList<String> validationMsg) {
		
		if(professor.getNome() == null || professor.getNome().isEmpty()) {
			validationMsg.add("Nome não pode ser vazio");
			return false;
		}
		
		if(professor.getSobrenome() == null || professor.getSobrenome().isEmpty()) {
			validationMsg.add("Sobrenome não pode ser vazio");
			return false;
		}
		
		if(professor.getCurso() == null || professor.getCurso().isEmpty()) {
			validationMsg.add("Curso não pode ser vazio");
			return false;
		}
		
		if(professor.getTurno() == null || professor.getTurno().isEmpty()) {
			validationMsg.add("Turno não pode ser vazio");
			return false;
		}
		
		if(professor.getEmail() == null || professor.getEmail().isEmpty()) {
			validationMsg.add("Email não pode ser vazio");
			return false;
		}
		if(!validaEmail(professor.getEmail())){
			validationMsg.add("Email inválido");
			return false;
		}
		
		if(professor.getTelefone() == null || professor.getTelefone().isEmpty()) {
			validationMsg.add("Telefone não pode ser vazio");
			return false;
		}
		
		return true;
	}

	public boolean validaEmail(String Email){
		
		return Email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");

	}
}