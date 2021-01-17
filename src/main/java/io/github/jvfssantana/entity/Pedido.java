package io.github.jvfssantana.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {
	
	private Integer id;
	private Cliente cliente;
	private LocalDate data_pedido;
	private BigDecimal total;
	
	public Pedido(Integer id, Cliente cliente, LocalDate data_pedido, BigDecimal total) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.data_pedido = data_pedido;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
