package com.yuriranieri.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuriranieri.cursomc.domain.Categoria;
import com.yuriranieri.cursomc.domain.Produto;
import com.yuriranieri.cursomc.repositories.CategoriaRepository;
import com.yuriranieri.cursomc.repositories.ProdutoRepository;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) throws ObjectNotFoundException {
		return produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrada para o id: " + id + " do tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}
