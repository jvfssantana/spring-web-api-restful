package io.github.jvfssantana.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jvfssantana.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer>{
	
//	@Query(value = "select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true) SQL 
	@Query(value = "select c from Cliente c where c.nome like :nome") // HQL
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	List<Cliente> findByNomeOrId(String nome, Integer id);
	
	@Query(value = "delete from Cliente c where c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);
}