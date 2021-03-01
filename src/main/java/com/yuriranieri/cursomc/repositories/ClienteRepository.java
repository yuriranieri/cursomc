package com.yuriranieri.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuriranieri.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
