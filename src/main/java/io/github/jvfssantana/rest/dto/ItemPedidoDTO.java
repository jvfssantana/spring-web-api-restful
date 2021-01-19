package io.github.jvfssantana.rest.dto;

public class ItemPedidoDTO {
	
	private Integer produto;
	private Integer quantidade;
	
	public ItemPedidoDTO() {
		
	}
	
	public ItemPedidoDTO(Integer produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	private Integer getProduto() {
		return produto;
	}

	private void setProduto(Integer produto) {
		this.produto = produto;
	}
	
	private Integer getQuantidade() {
		return quantidade;
	}
	
	private void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
