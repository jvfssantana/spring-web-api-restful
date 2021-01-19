package io.github.jvfssantana.service;

import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvarPedido(PedidoDTO dto);

}
