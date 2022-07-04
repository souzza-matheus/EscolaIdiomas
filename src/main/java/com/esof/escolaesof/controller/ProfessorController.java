package com.esof.escolaesof.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.esof.escolaesof.dto.AlunoDTO;
import com.esof.escolaesof.dto.ProfessorDTO;
import com.esof.escolaesof.model.Aluno;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/escolaIdiomas/professor")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessorController {

	private final  ProfessorRepository _professorRepository;
	private final ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> Listar(){
		
		return ResponseEntity.ok(_professorRepository.findAll().stream().map(this::entityToDto)
				.collect(Collectors.toList()));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody @Valid ProfessorDTO _professor) throws SQLException {

		ArrayList<String> _validationMsgs = new ArrayList<String>();
		String erroMsg = "";
		
		if(validarProfessor(dtoToEntity(_professor), _validationMsgs)){
			
			try {			
				_professorRepository.save(dtoToEntity(_professor));
				return "Cadastro realizado com sucesso!";
				
			} catch (Exception e) {	

				return "Falha ao realizar o cadastro";
			}	
		}
		else{
			for (String erro : _validationMsgs) {
				erroMsg += erro + ", ";
				
			}
			return "Falha ao realizar o cadastro: " + erroMsg;
		}
	
	
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<ProfessorDTO> findById(@PathVariable long id){
	   return _professorRepository.findById(id)
	           .map(record -> ResponseEntity.ok().body(entityToDto(record)))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable("id") long id, @RequestBody @Valid ProfessorDTO professor) {
	   return _professorRepository.findById(id)
	           .map(Professor -> {
	               Professor.setNome(professor.getNome());
	               Professor.setSobrenome(professor.getSobrenome());
	               Professor.setCurso(professor.getCurso());
	               Professor.setTurno(professor.getTurno());
	               Professor.setEmail(professor.getEmail());
	               Professor.setTelefone(professor.getTelefone());
	               Professor updated = _professorRepository.save(dtoToEntity(professor));
	               return ResponseEntity.ok().body(entityToDto(updated));
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

	private ProfessorDTO entityToDto(Professor professor){
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.LOOSE);
		new ProfessorDTO();
		return modelMapper.map(professor,ProfessorDTO.class);
	}

	private Professor dtoToEntity(ProfessorDTO professorDTO){
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.LOOSE);
		new Professor();
		return modelMapper.map(professorDTO,Professor.class);
	}
}