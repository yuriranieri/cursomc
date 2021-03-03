package com.yuriranieri.cursomc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuriranieri.cursomc.domain.Pedido;
import com.yuriranieri.cursomc.services.PedidoService;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Pedido pedido = pedidoService.find(id);

		return ResponseEntity.ok().body(pedido);
	}

}
