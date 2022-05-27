package com.esof.escolaesof.controller;
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
import com.esof.escolaesof.model.Curso;
import com.esof.escolaesof.repository.CursoRepository;


@RestController
@RequestMapping("api/v1/escolaIdiomas/curso")
public class CursoController {

    @Autowired
	private CursoRepository _cursoRepository;

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@RequestBody Curso _curso) throws SQLException {

        ArrayList<String> _validationMsgs = new ArrayList<String>();
        String erroMsg = "";

        try {

            if (validarCurso(_curso, _validationMsgs)) {
                _cursoRepository.save(_curso);
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
    public List<Curso> listar() {
        return _cursoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity FindById(@PathVariable long id){
        return _cursoRepository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
        
    }

    @PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Curso curso) {
	   return _cursoRepository.findById(id)
       .map(Curso -> {
        Curso.setNome(curso.getNome());
        Curso.setIdioma(curso.getIdioma());
        Curso.setNivel(curso.getNivel());
        Curso.setTurno(curso.getTurno());
        Curso updated = _cursoRepository.save(curso);
        return ResponseEntity.ok().body(updated);
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

       
   
    
}
