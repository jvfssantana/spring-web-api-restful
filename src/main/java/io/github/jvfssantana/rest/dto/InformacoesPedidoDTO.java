package io.github.jvfssantana.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesPedidoDTO {
	private Integer codigo;
	private String cpfCliente;
	private String nomeCliente;
	private String data_pedido;
	private BigDecimal total;
	private String status;
	private List<InformacaoItemPedidoDTO> items;
}
