package com.esof.escolaesof.dto;

import com.esof.escolaesof.model.Aluno;
import com.esof.escolaesof.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long matricula;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private Curso curso;

    @NotNull
    @NotBlank
    private int idade;

    private String email;

    private String telefone;

    private String nome_responsavel;

    private String telefone_responsavel;

    private String email_responsavel;
}
