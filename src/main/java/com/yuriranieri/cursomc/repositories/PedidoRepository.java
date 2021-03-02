package com.yuriranieri.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuriranieri.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
