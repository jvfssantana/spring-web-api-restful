package io.github.jvfssantana.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
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

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.repository.Clientes;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private Clientes clientes; 
	
	public ClienteController(Clientes clientes) {
		this.clientes = clientes;
	}

	@GetMapping("{id}")
	public Cliente buscarClienteById(@PathVariable Integer id) {
		
		return clientes
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")); 
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvarCliente(@RequestBody Cliente cliente) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaCliente(@PathVariable Integer id) {
		
		clientes.findById(id)
			.map(cliente -> {
				clientes.delete(cliente);
				return cliente;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id,  @RequestBody Cliente cliente) {
		clientes
			.findById(id)
			.map(clienteExistente -> {
				cliente.setId(clienteExistente.getId());
				clientes.save(cliente);
				return clienteExistente;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping
	public List<Cliente> buscarTodosClientes(Cliente lista) {
		
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(lista, matcher);
		return clientes.findAll(example); 
		
	}
}
