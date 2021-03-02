package com.yuriranieri.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuriranieri.cursomc.domain.Pedido;
import com.yuriranieri.cursomc.repositories.PedidoRepository;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrada para o id: " + id + " do tipo: " + Pedido.class.getName()));
	}

}
