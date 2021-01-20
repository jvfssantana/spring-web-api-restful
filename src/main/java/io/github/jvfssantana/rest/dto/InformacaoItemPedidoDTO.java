package io.github.jvfssantana.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacaoItemPedidoDTO {
	private String descricaoProduto;
	private BigDecimal preco_unitario;
	private Integer quantidade;
}
