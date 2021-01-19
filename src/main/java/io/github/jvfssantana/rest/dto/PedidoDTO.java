package io.github.jvfssantana.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
	
	public PedidoDTO() {
		
	}
	
	public PedidoDTO(Integer cliente, BigDecimal total, List<ItemPedidoDTO> items) {
		super();
		this.cliente = cliente;
		this.total = total;
		this.items = items;
	}

	private Integer getCliente() {
		return cliente;
	}

	private void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	private BigDecimal getTotal() {
		return total;
	}

	private void setTotal(BigDecimal total) {
		this.total = total;
	}

	private List<ItemPedidoDTO> getItems() {
		return items;
	}

	private void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}
	
}
