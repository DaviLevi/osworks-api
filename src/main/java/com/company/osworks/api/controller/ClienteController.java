package com.company.osworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.osworks.domain.model.Cliente;
import com.company.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private CadastroClienteService cadastroClienteService;
	
	public ClienteController(CadastroClienteService cadastroClienteService) {
		this.cadastroClienteService = cadastroClienteService;
	}
	
	@GetMapping
	public List<Cliente> listar() {
		return cadastroClienteService.listarClientes();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> listarPeloID(@PathVariable Long id) throws NotFoundException {
		Cliente cliente = cadastroClienteService.buscarPeloID(id);
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente listarPeloID(@RequestBody @Valid Cliente cliente) throws NotFoundException {
		return cadastroClienteService.salvar(cliente);
		
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Cliente> atualizarPeloID(@RequestBody @Valid Cliente cliente, @PathVariable Long id) throws NotFoundException {
		Cliente clienteAtualizado = cadastroClienteService.salvar(cliente);
		return ResponseEntity.ok(clienteAtualizado);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Cliente> deletarPeloID(@PathVariable Long id) throws NotFoundException {
		cadastroClienteService.deletarPeloID(id);
		return ResponseEntity.noContent().build();
	}
}
