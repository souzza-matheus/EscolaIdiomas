package com.esof.escolaesof.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.esof.escolaesof.dto.AlunoDTO;
import com.esof.escolaesof.exception.ContatoDeResponsavelNotFoundException;
import com.esof.escolaesof.exception.NomeDeResponsavelNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.esof.escolaesof.exception.AlunoNotFoundException;
import com.esof.escolaesof.model.Aluno;
import com.esof.escolaesof.repository.AlunoRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/escolaIdiomas/aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<AlunoDTO> listarTodosAlunos(@RequestParam(required= false) Long curso_id){

        if(curso_id == null) {
            return alunoRepository.findAll().stream().map(this::entityToDto)
                    .collect(Collectors.toList());

        }else {
            return alunoRepository.findByCursoId(curso_id).stream().map(this::entityToDto).collect(Collectors.toList());
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody @Valid AlunoDTO aluno ) throws ContatoDeResponsavelNotFoundException, NomeDeResponsavelNotFoundException {
        if(aluno.getIdade() < 18 && aluno.getEmail_responsavel() == null || aluno.getIdade() < 18 && aluno.getTelefone_responsavel() == null ){
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
        }
        if(aluno.getIdade() < 18 && aluno.getNome_responsavel() == null){
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Aluno novoAluno=  alunoRepository.save(dtoToEntity(aluno));
        return new ResponseEntity<>(entityToDto(novoAluno), HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    @Transactional
    public ResponseEntity<AlunoDTO> atualizarAluno(@RequestBody @Valid AlunoDTO alunoAtualizado, @PathVariable Long matricula) throws AlunoNotFoundException{
       Aluno aluno = verifyIfExists(matricula);
        aluno.setEmail_responsavel(alunoAtualizado.getEmail_responsavel());
        aluno.setTelefone_responsavel(alunoAtualizado.getTelefone_responsavel());
        aluno.setCurso(alunoAtualizado.getCurso());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        alunoRepository.save(aluno);
        return new ResponseEntity<>(entityToDto(aluno), HttpStatus.OK);
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


    public boolean validaEmail(String Email){

		return Email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");

	}

	private AlunoDTO entityToDto(Aluno aluno){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        new AlunoDTO();
       return modelMapper.map(aluno,AlunoDTO.class);
    }

    private Aluno dtoToEntity(AlunoDTO aluno){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        new Aluno();
        return modelMapper.map(aluno,Aluno.class);
    }


}