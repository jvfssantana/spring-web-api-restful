package io.github.jvfssantana;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.repository.Clientes;
import io.github.jvfssantana.repository.Pedidos;

@SpringBootApplication
public class JpaApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			
			System.out.println("Salvando clientes!");
			
			Cliente cliente = new Cliente("João");
			clientes.save(cliente);
			
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setData_pedido(LocalDate.now());
			pedido.setTotal(BigDecimal.valueOf(10));
			
			pedidos.save(pedido);
			
			
//			Cliente clientePedidos = clientes.findClienteFetchPedidos(cliente.getId());
//			System.out.println(clientePedidos);
//			System.out.println(clientePedidos.getPedidos());
			
			pedidos.findByCliente(cliente).forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	
}
