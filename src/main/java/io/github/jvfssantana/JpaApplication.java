package io.github.jvfssantana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.repository.Clientes;

@SpringBootApplication
public class JpaApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			Cliente cliente = new Cliente("João");
			clientes.save(cliente);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	
}
