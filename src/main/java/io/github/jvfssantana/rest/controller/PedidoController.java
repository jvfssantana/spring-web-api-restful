package io.github.jvfssantana.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;

import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.rest.dto.PedidoDTO;
import io.github.jvfssantana.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {	
		this.pedidoService = pedidoService;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer salvarPedido(@RequestBody PedidoDTO dto) {
		Pedido pedido = pedidoService.salvarPedido(dto);
		return pedido.getId();
	}
	
}
