package com.yuriranieri.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuriranieri.cursomc.domain.Cliente;
import com.yuriranieri.cursomc.repositories.ClienteRepository;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado para o id: " + id + " do tipo: " + Cliente.class.getName()));
	}

}
