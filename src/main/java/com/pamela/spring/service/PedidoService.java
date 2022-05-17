package com.pamela.spring.service;

import com.pamela.spring.domain.entity.Pedido;
import com.pamela.spring.domain.enums.StatusPedido;
import com.pamela.spring.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
