package io.github.jvfssantana.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import io.github.jvfssantana.entity.Produto;
import io.github.jvfssantana.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private ProdutoRepository produtos;

	public ProdutoController(ProdutoRepository produtos) {
		this.produtos = produtos;
	}

	@GetMapping("{id}")
	public Produto buscaProdutoById(@PathVariable Integer id) {
		return produtos.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado!"));		
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Produto salvaProduto(@RequestBody @Valid Produto produto) {
		return produtos.save(produto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void deletaProduto(@PathVariable Integer id) {
		produtos.findById(id).map(produto -> {
				produtos.delete(produto);
				return produto;	
			}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado!"));		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void atualizarProduto(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
		produtos.findById(id).map(p -> {
				produto.setId(p.getId());
				produtos.save(produto);
				return p;
			}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado!"));
	}
	
	@GetMapping
	public List<Produto> buscaTodosProdutos(Produto lista) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(lista, matcher);
		return produtos.findAll(example);
		
	}
}