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

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository; 
	
	public ClienteController(ClienteRepository clientes) {
		this.clienteRepository = clientes;
	}

	@GetMapping("{id}")
	public Cliente buscaClienteById(@PathVariable Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado")); 
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Cliente salvarCliente(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void deletaCliente(@PathVariable Integer id) {		
		clienteRepository.findById(id).map(cliente -> {
				clienteRepository.delete(cliente);
				return cliente;
			}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void atualizarCliente(@PathVariable Integer id,  @RequestBody @Valid Cliente cliente) {
		clienteRepository.findById(id).map(c -> {
				cliente.setId(c.getId());
				clienteRepository.save(cliente);
				return c;
			}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	public List<Cliente> buscaTodosClientes(Cliente lista) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(lista, matcher);
		return clienteRepository.findAll(example); 
	}
	
}