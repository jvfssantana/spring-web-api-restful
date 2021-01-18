package io.github.jvfssantana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

		
	List<Pedido> findByCliente(Cliente cliente);
	
	
}
