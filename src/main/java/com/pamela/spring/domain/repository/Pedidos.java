package com.pamela.spring.domain.repository;

import com.pamela.spring.domain.entity.Cliente;
import com.pamela.spring.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
