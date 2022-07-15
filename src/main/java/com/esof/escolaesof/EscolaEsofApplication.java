package com.esof.escolaesof;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.esof.escolaesof")
public class EscolaEsofApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaEsofApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper(){
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper;
    }

}
