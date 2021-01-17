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
			clientes.salvarCliente(new Cliente("João"));
			clientes.salvarCliente(new Cliente("Maria"));
			
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Atualizando todos os clientes!");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado!");
				clientes.atualizar(c);
			});
			
			todosClientes.forEach(System.out::println);
			
			System.out.println("Buscando por nome o cliente!");
			clientes.buscarPorNome("Jo").forEach(System.out::println);
			
			System.out.println("Deletando todos os clientes!");
			clientes.obterTodos().forEach(c -> {
				clientes.deletar(c);
			});

			todosClientes = clientes.obterTodos();
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
