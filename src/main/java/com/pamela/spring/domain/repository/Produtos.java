package com.pamela.spring.domain.repository;

import com.pamela.spring.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {



}
