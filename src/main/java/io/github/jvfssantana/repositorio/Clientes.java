package io.github.jvfssantana.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.jvfssantana.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer>{
	List<Cliente> findByNomeLike(String nome);
}