package com.pamela.spring.service;

import com.pamela.spring.domain.entity.Pedido;
import com.pamela.spring.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
