package io.github.jvfssantana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jvfssantana.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
}
