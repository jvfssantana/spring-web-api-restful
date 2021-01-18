package io.github.jvfssantana;

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
			
			boolean existe = clientes.existsByNome("João");
			System.out.println("Existe um cliente com o nome João? " + existe);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	
}
