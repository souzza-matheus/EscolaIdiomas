package com.esof.escolaesof.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String sobrenome;

	@OneToOne
	@JoinColumn(name = "curso_id", referencedColumnName = "id")
	@JsonManagedReference
	private Curso curso;

	private String turno;

	private String email;

	private String telefone;

}
