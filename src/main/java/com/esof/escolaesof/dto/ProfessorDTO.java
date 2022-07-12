package com.esof.escolaesof.dto;

import com.esof.escolaesof.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String sobrenome;

    @NotNull
    @NotBlank
    private Curso curso;

    @NotNull
    @NotBlank
    private String turno;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String telefone;
}
