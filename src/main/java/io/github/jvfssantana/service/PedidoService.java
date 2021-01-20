package io.github.jvfssantana.service;

import java.util.Optional;

import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.enums.StatusPedido;
import io.github.jvfssantana.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvarPedido(PedidoDTO dto);
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizaStatusPedido(Integer id, StatusPedido statusPedido);
}
