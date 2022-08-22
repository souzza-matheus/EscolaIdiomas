package com.esof.escolaesof.config;

import com.esof.escolaesof.model.User;
import com.esof.escolaesof.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFIlter extends OncePerRequestFilter {


    private TokenService tokenService;
    private UserRepository usuarioRepository;



    public TokenFIlter(TokenService tokenService, UserRepository usuarioRepository) {

        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isValid(token);
        if (valido) {
            autenticar(token);
        }

        filterChain.doFilter(request, response);
    }


    private void autenticar(String token) {

        Long idUsuario = tokenService.getIdUsuario(token);
        User usuario = usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String recuperarToken(HttpServletRequest request) {
        String token= request.getHeader("Authorization");
        if(token == null || token.isEmpty()|| !token.startsWith("Bearer")) {
            return null;

        }else {
            return token.substring(7);

        }
    }
}
