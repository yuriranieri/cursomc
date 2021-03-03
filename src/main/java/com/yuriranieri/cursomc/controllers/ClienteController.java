package com.yuriranieri.cursomc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuriranieri.cursomc.domain.Cliente;
import com.yuriranieri.cursomc.services.ClienteService;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Cliente cliente = clienteService.find(id);

		return ResponseEntity.ok().body(cliente);
	}

}
