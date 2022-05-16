package com.pamela.spring;

import com.pamela.spring.domain.entity.Cliente;
import com.pamela.spring.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
//		return args -> {
//		Cliente c = new Cliente(null, "fulano");
//		clientes.save(c);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
