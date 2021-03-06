package io.github.jvfssantana.service.implementacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jvfssantana.entity.Cliente;
import io.github.jvfssantana.entity.ItemPedido;
import io.github.jvfssantana.entity.Pedido;
import io.github.jvfssantana.entity.Produto;
import io.github.jvfssantana.enums.StatusPedido;
import io.github.jvfssantana.exception.PedidoNaoEncontradoException;
import io.github.jvfssantana.exception.RegrasNegocioException;
import io.github.jvfssantana.repository.ClienteRepository;
import io.github.jvfssantana.repository.ItemPedidoRepository;
import io.github.jvfssantana.repository.PedidoRepository;
import io.github.jvfssantana.repository.ProdutoRepository;
import io.github.jvfssantana.rest.dto.ItemPedidoDTO;
import io.github.jvfssantana.rest.dto.PedidoDTO;
import io.github.jvfssantana.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImplementacao implements PedidoService {
	
	private final PedidoRepository pedidosRepository;
	private final ClienteRepository clientesRepository;
	private final ProdutoRepository produtosRepository;
	private final ItemPedidoRepository itemsPedidoRepository;
	
	@Override
	@Transactional
	public Pedido salvarPedido(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegrasNegocioException("Código de cliente inválido!"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setData_pedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidosRepository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
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
					Produto produto = produtosRepository.findById(idProduto).orElseThrow(() -> new RegrasNegocioException("Código de produto inválido: " + idProduto));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					return itemPedido;
					
				}).collect(Collectors.toList()); 		
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidosRepository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizaStatusPedido(Integer id, StatusPedido statusPedido) {
		pedidosRepository.findById(id).map(pedido -> {
			pedido.setStatus(statusPedido);
			return pedidosRepository.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));		
	}
}