package com.pamela.spring;

import com.pamela.spring.domain.entity.Cliente;
import com.pamela.spring.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			System.out.println("Salvando Clientes");

			clientes.salvar(new Cliente("Pamela"));
			clientes.salvar(new Cliente("Alef"));
			clientes.salvar(new Cliente("fulano"));


			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);


			System.out.println("Atualizando Clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado");
				clientes.atualizar(c);
			});

			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando Clientes");
			clientes.buscarPorNome("ful").forEach(System.out::println);

//			System.out.println("Deletando Clientes");
//			clientes.obterTodos().forEach(c -> {
//					clientes.deletar(c);
//			});

			todosClientes = clientes.obterTodos();
			if (todosClientes.isEmpty()){
				System.out.println("Nenhum Cliente Encontrado");
			} else {
				todosClientes.forEach(System.out::println);
			}



		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
