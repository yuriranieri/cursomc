package com.yuriranieri.cursomc.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuriranieri.cursomc.domain.Cliente;
import com.yuriranieri.cursomc.dto.ClienteDto;
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
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<Cliente> categorias = clienteService.findAll();
		List<ClienteDto> dto = categorias.stream().map(ClienteDto::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> categorias = clienteService.findPage(page, linesPerPage, orderBy, direction);

		Page<ClienteDto> dto = categorias.map(ClienteDto::new);

		return ResponseEntity.ok().body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody @Valid ClienteDto dto, @PathVariable Integer id) {
		Cliente entity = clienteService.fromDto(dto);
		entity.setId(id);
		entity = clienteService.update(entity);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
