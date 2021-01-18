package io.github.jvfssantana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jvfssantana.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{
	
	
	
}
