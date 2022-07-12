package com.esof.escolaesof.dto;


import com.esof.escolaesof.model.Professor;
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
public class CursoDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String codigo;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String idioma;

    @NotNull
    @NotBlank
    private String nivel;

    @NotNull
    @NotBlank
    private String turno;

    @NotNull
    @NotBlank
    private Professor professor;

}
