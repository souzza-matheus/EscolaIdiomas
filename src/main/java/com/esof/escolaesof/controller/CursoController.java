package com.esof.escolaesof.controller;
import com.esof.escolaesof.dto.AlunoDTO;
import com.esof.escolaesof.dto.CursoDTO;
import com.esof.escolaesof.model.Aluno;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.esof.escolaesof.model.Curso;
import com.esof.escolaesof.repository.CursoRepository;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/escolaIdiomas/curso")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoController {


	private CursoRepository _cursoRepository;
    private final ModelMapper modelMapper;

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@RequestBody @Valid CursoDTO _curso) throws SQLException {

        ArrayList<String> _validationMsgs = new ArrayList<String>();
        String erroMsg = "";

        try {

            if (validarCurso(dtoToEntity(_curso), _validationMsgs)) {
                _cursoRepository.save(dtoToEntity(_curso));
                return "Cadastro realizado com sucesso!";

            } else {
                for (String erro : _validationMsgs) {
                    erroMsg += erro + ", ";
                }
                return "Falha ao realizar o cadastro: " + erroMsg;
            }

        } catch (Exception e) {

            return "Falha ao realizar o cadastro: " + e.getMessage();
        } 
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        return ResponseEntity.ok(_cursoRepository.findAll().stream().map(this::entityToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CursoDTO> FindById(@PathVariable long id){
        return _cursoRepository.findById(id)
	           .map(record -> ResponseEntity.ok().body(entityToDto(record)))
	           .orElse(ResponseEntity.notFound().build());
        
    }

    @PutMapping(value="/{id}")
	public ResponseEntity<CursoDTO> update(@PathVariable("id") long id, @RequestBody @Valid CursoDTO curso) {
	   return _cursoRepository.findById(id)
       .map(Curso -> {
        Curso.setNome(curso.getNome());
        Curso.setIdioma(curso.getIdioma());
        Curso.setNivel(curso.getNivel());
        Curso.setTurno(curso.getTurno());
        Curso updated = _cursoRepository.save(dtoToEntity(curso));
        return ResponseEntity.ok().body(entityToDto(updated));
    }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return _cursoRepository.findById(id)
	           .map(Curso -> {
	               _cursoRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
    
    public boolean validarCurso(Curso _curso, ArrayList<String> _validationMsgs) {

        if (_curso.getNome() == null || _curso.getNome().isEmpty()) {
            _validationMsgs.add("Nome do curso é obrigatório");
        }

        if (_curso.getIdioma() == null || _curso.getIdioma().isEmpty()) {
            _validationMsgs.add("Idioma do curso é obrigatório");
        }
        if(_curso.getNivel() == null || _curso.getNivel().isEmpty()){
            _validationMsgs.add("Nivel do curso é obrigatório");
        }

        return true;

       
    }

    private CursoDTO entityToDto(Curso curso){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        new CursoDTO();
        return modelMapper.map(curso,CursoDTO.class);
    }

    private Curso dtoToEntity(CursoDTO curso){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        new Curso();
        return modelMapper.map(curso,Curso.class);
    }

       
   
    
}
