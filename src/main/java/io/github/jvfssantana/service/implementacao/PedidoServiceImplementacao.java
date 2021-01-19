package io.github.jvfssantana.service.implementacao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.entity.ItemPedido;
import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.entity.Produto;
import io.github.jvfssantana.exception.RegrasNegocioException;
import io.github.jvfssantana.repository.Clientes;
import io.github.jvfssantana.repository.ItemsPedido;
import io.github.jvfssantana.repository.Pedidos;
import io.github.jvfssantana.repository.Produtos;
import io.github.jvfssantana.rest.dto.ItemPedidoDTO;
import io.github.jvfssantana.rest.dto.PedidoDTO;
import io.github.jvfssantana.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImplementacao implements PedidoService {
	
	private final Pedidos pedidos;
	private final Clientes clientes;
	private final Produtos produtos;
	private final ItemsPedido itemsPedidoRepository;
	
	@Override
	@Transactional
	public Pedido salvarPedido(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientes.findById(idCliente).orElseThrow(() -> new RegrasNegocioException("Código de cliente inválido!"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setData_pedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidos.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setPedidos(itemsPedido);
		return pedido;
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOs) {
		if(itemPedidoDTOs.isEmpty()) {
			throw new RegrasNegocioException("Não é possível realizar um pedido sem items!");
		}
		
		return itemPedidoDTOs
				.stream()
				.map(dto -> {
					Integer idProduto = dto.getProduto();
					Produto produto = produtos.findById(idProduto).orElseThrow(() -> new RegrasNegocioException("Código de produto inválido: " + idProduto));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					return itemPedido;
					
				}).collect(Collectors.toList()); 		
	}
}
