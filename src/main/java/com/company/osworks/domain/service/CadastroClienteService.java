package com.company.osworks.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.company.osworks.domain.exception.NegocioException;
import com.company.osworks.domain.model.Cliente;
import com.company.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	private final ClienteRepository clienteRepository;
	
	public CadastroClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteExistente!=null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já Existe um cliente cadastrado com este email.");
		}
		return clienteRepository.save(cliente);
	}
	
	public void deletar(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	public void deletarPeloID(Long id) {
		try {
			clienteRepository.deleteById(id);
		}catch(EmptyResultDataAccessException exc) {
			throw new EntityNotFoundException("Nenhum cadastro encontrado para cliente com ID " + id);
		}
		
	}
	
	public Cliente buscarPeloID(Long id) throws EntityNotFoundException{
		return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum cadastro encontrado para cliente com ID " + id));                     
	}
	
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
}
