package com.pamela.spring.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }

}
