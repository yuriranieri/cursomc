package com.yuriranieri.cursomc.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuriranieri.cursomc.domain.Categoria;
import com.yuriranieri.cursomc.dto.CategoriaDto;
import com.yuriranieri.cursomc.services.CategoriaService;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDto> dto = categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Categoria categoria = categoriaService.find(id);

		return ResponseEntity.ok().body(categoria);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Integer id) throws ObjectNotFoundException {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

	

}
