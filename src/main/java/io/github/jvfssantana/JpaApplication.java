package io.github.jvfssantana;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.repositorio.Clientes;

@SpringBootApplication
public class JpaApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			
			System.out.println("Salvando clientes!");
			clientes.save(new Cliente("João"));
			clientes.save(new Cliente("Maria"));
			
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Atualizando todos os clientes!");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado!");
				clientes.save(c);
			});
			
			todosClientes.forEach(System.out::println);
			
			System.out.println("Buscando por nome o cliente!");
			clientes.findByNomeLike("Jo").forEach(System.out::println);
			
			System.out.println("Deletando todos os clientes!");
			clientes.findAll().forEach(c -> {
				clientes.delete(c);
			});

			todosClientes = clientes.findAll();
			if(todosClientes.isEmpty() ) {
				System.out.println("Nenhum clientes encontrado!");
			} else {
				todosClientes.forEach(System.out::println);
			}
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	
}
