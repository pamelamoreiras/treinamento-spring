package com.pamela.spring.service.impl;

import com.pamela.spring.domain.entity.Cliente;
import com.pamela.spring.domain.entity.ItemPedido;
import com.pamela.spring.domain.entity.Pedido;
import com.pamela.spring.domain.entity.Produto;
import com.pamela.spring.domain.enums.StatusPedido;
import com.pamela.spring.domain.repository.Clientes;
import com.pamela.spring.domain.repository.ItensPedido;
import com.pamela.spring.domain.repository.Pedidos;
import com.pamela.spring.domain.repository.Produtos;
import com.pamela.spring.exception.PedidoNaoEncontradoException;
import com.pamela.spring.exception.RegraDeNegocioException;
import com.pamela.spring.rest.dto.ItemPedidoDTO;
import com.pamela.spring.rest.dto.PedidoDTO;
import com.pamela.spring.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final  Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() ->
                        new RegraDeNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();

        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItens(pedido, dto.getItens());

        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens ) {
        if (itens.isEmpty()){
            throw new RegraDeNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens.stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                   Produto produto = produtosRepository
                           .findById(idProduto)
                           .orElseThrow(
                                    () -> new RegraDeNegocioException("Código de produto inválido: " + idProduto
                            ));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
