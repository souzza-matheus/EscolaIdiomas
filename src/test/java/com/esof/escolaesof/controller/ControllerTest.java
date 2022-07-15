package com.esof.escolaesof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.esof.escolaesof.dto.AlunoDTO;
import com.esof.escolaesof.dto.ProfessorDTO;
import com.esof.escolaesof.model.Aluno;
import com.esof.escolaesof.model.Curso;
import com.esof.escolaesof.model.Professor;
import com.esof.escolaesof.repository.AlunoRepository;
import com.esof.escolaesof.repository.CursoRepository;
import com.esof.escolaesof.repository.ProfessorRepository;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class ControllerTest {
	
	@Autowired
	private AlunoController alunoController;
	@Autowired
	private ProfessorController professorController;
	@Autowired
	private CursoController cursoController;
	
	@MockBean
	private AlunoRepository alunoRepository;
	@MockBean
	private CursoRepository cursoRepository;
	@MockBean
	private ProfessorRepository professorRepository;
	
	@Mock
	private Aluno aluno;
	
	@Mock
	private Professor professor;
	
	@BeforeEach
	public void setup() {
		
		standaloneSetup(this.cursoController, this.professorController, this.alunoController);
			}
	
	@Test
	public void ListarTodosAlunos_Sucesso() {	
		
		MockHttpServletRequest request  = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Curso curso = new Curso();
		
		Aluno Thulio = new Aluno(1L,"Thulio", curso, 27,"thulio.fonseca@gmail.com", "9999-9999", null, null, null);
		Aluno Matheus = new Aluno(2L,"Matheus", curso, 27,"Matheus.Silva@gmail.com", "9999-9999", null, null, null);
		List<Aluno> Alunos =  new ArrayList<Aluno>();
		
		Alunos.add(Thulio);
		Alunos.add(Matheus);
		
		when(alunoRepository.findAll()).thenReturn(Alunos);	
				
		assertThat(alunoRepository.findAll().size()).isEqualTo(2);
		
		
	}
	
	@Test
	public void AddAluno_Sucesso() {		
		
		MockHttpServletRequest request  = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
		
		Curso curso = new Curso();
		AlunoDTO alunoDTO = new AlunoDTO(1L,"Thulio", curso, 27,"thulio.fonseca@gmail.com", "9999-9999", null, null, null);

		ResponseEntity<AlunoDTO> responseEntity = alunoController.cadastrarAluno(alunoDTO);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		//assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
		
	}
	
	@Test
	public void AddAluno_Falha() {		
		
		MockHttpServletRequest request  = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
		
		Curso curso = new Curso();
		AlunoDTO alunoDTO = new AlunoDTO(1L,"Thulio", curso, 17,"thulio.fonseca@gmail.com", "9999-9999", null, null, null);

		ResponseEntity<AlunoDTO> responseEntity = alunoController.cadastrarAluno(alunoDTO);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
		//assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
		
	}
	
	@Test
	public void AddProfessor_Sucesso() {
		
		MockHttpServletRequest request  = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(professorRepository.save(any(Professor.class))).thenReturn(professor);
		
		
		Curso curso = new Curso();
		ProfessorDTO professorDTO = new ProfessorDTO(1L, "Claudio", "Vieira", curso, "noite", "claudio.vieira@hotmail.com", "7777-7777");

		ResponseEntity<ProfessorDTO> responseEntity = professorController.cadastrar(professorDTO);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		//assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
		
	}
}
