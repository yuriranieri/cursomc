package com.yuriranieri.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuriranieri.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
