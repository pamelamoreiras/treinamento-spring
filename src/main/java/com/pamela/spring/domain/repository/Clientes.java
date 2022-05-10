package com.pamela.spring.domain.repository;

import com.pamela.spring.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository <Cliente,Integer>{

    //List<Cliente> findByNomeLike(String nome);

    @Query(value = "Select * from cliente c where c.nome like '%:nome%' ",
    nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "delete from Cliente c where c.nome =:nome ")
    @Modifying //o @Querry é opcional colocar
    void deletePorNome(String nome);

    boolean existsByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id =:id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
