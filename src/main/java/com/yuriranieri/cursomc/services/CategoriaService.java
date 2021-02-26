package com.yuriranieri.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuriranieri.cursomc.domain.Categoria;
import com.yuriranieri.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
