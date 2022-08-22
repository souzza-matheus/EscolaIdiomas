package com.esof.escolaesof.controller;


import com.esof.escolaesof.config.TokenService;
import com.esof.escolaesof.dto.LoginForm;
import com.esof.escolaesof.dto.TokenDTO;

import com.esof.escolaesof.exception.ResourceExceptionHandler;
import com.esof.escolaesof.exception.StandardError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginAndRegistrationController extends ResourceExceptionHandler {


    private AuthenticationManager authManager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginForm form) {

        System.out.println(form.getEmail());
        System.out.println(form.getPassword());
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            System.out.println("Bateu aqui");
            Authentication authentication = authManager.authenticate(dadosLogin);
            System.out.println("Bateu aqui");
            String token = tokenService.gerarToken(authentication);
            System.out.print(token);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return new ResponseEntity<Object>(new StandardError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


}
