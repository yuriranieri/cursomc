package com.yuriranieri.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuriranieri.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
