package io.github.jvfssantana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jvfssantana.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

	
	
}
