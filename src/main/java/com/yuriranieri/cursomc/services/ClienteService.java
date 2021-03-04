package com.yuriranieri.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuriranieri.cursomc.domain.Cliente;
import com.yuriranieri.cursomc.dto.ClienteDto;
import com.yuriranieri.cursomc.repositories.ClienteRepository;
import com.yuriranieri.cursomc.services.exceptions.DataIntegrityException;
import com.yuriranieri.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado para o id: " + id + " do tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		
		return clienteRepository.save(newCliente);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDto dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}

}
