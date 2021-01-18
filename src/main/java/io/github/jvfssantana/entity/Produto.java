package io.github.jvfssantana.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "descricao", length = 100)
	private String descricao;
	
	@Column(name = "preco_unitario")
	private BigDecimal preco_unitario;
	
	public Produto(Integer id, String descricao, BigDecimal preco_unitario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco_unitario = preco_unitario;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPreco_unitario() {
		return preco_unitario;
	}
	
	public void setPreco_unitario(BigDecimal preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
	
}
