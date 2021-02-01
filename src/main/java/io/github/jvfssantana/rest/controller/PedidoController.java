package io.github.jvfssantana.rest.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.github.jvfssantana.entity.ItemPedido;
import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.enums.StatusPedido;
import io.github.jvfssantana.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.jvfssantana.rest.dto.InformacaoItemPedidoDTO;
import io.github.jvfssantana.rest.dto.InformacoesPedidoDTO;
import io.github.jvfssantana.rest.dto.PedidoDTO;
import io.github.jvfssantana.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {	
		this.pedidoService = pedidoService;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer salvarPedido(@RequestBody @Valid PedidoDTO dto) {
		Pedido pedido = pedidoService.salvarPedido(dto);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoService.obterPedidoCompleto(id)
				.map(p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado!"));
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO statusPedidoDTO) {
		String novoStatus = statusPedidoDTO.getNovoStatus();
		pedidoService.atualizaStatusPedido(id, StatusPedido.valueOf(novoStatus));
	}
	
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
			.builder()
			.codigo(pedido.getId())
			.data_pedido(pedido.getData_pedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.nomeCliente(pedido.getCliente().getNome())
			.cpfCliente(pedido.getCliente().getCpf())
			.total(pedido.getTotal())
			.status(pedido.getStatus().name())
			.items(converter(pedido.getItens()))
			.build();
	}
	
	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(item -> InformacaoItemPedidoDTO
			.builder()
			.descricaoProduto(item.getProduto().getDescricao())
			.preco_unitario(item.getProduto().getPreco_unitario())
			.quantidade(item.getQuantidade())
			.build()).collect(Collectors.toList());
	}
}
