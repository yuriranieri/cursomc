package com.yuriranieri.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuriranieri.cursomc.domain.Categoria;
import com.yuriranieri.cursomc.dto.CategoriaDto;
import com.yuriranieri.cursomc.repositories.CategoriaRepository;
import com.yuriranieri.cursomc.services.exceptions.DataIntegrityException;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria find(Integer id) throws ObjectNotFoundException {
		return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada para o id: " + id + " do tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return categoriaRepository.findAll(pageRequest);
	}

	public Categoria fromDto(CategoriaDto dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
	
}
