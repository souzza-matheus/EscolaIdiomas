package com.esof.escolaesof;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@ComponentScan(basePackages = "com.esof.escolaesof")
public class EscolaEsofApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaEsofApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper(){
	    return  new ModelMapper();
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
