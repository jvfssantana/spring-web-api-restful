package io.github.jvfssantana.service.implementacao;

import org.springframework.stereotype.Service;

import io.github.jvfssantana.repository.Pedidos;
import io.github.jvfssantana.service.PedidoService;

@Service
public class PedidoServiceImplementacao implements PedidoService {
	
	private Pedidos pedidos;

	public PedidoServiceImplementacao(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
}
