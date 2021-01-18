package io.github.jvfssantana.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDate data_pedido;
	
	@Column(name = "total", precision = 20, scale = 2)
	private BigDecimal total;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> pedidos;
	
	public Pedido() {
		
	}

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

	public List<ItemPedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<ItemPedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data_pedido=" + data_pedido + ", total=" + total + "]";
	}

}
