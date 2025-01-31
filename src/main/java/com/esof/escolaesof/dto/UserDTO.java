package com.esof.escolaesof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String username;

    @NotNull
    @Size(min = 1, max = 200)
    private String email;

    @NotNull
    @Size(min = 1, max = 200)
    private String password;


}
