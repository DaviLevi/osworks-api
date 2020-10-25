package com.company.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.osworks.api.contract.input.OrdemServicoInput;
import com.company.osworks.api.contract.model.OrdemServicoModel;
import com.company.osworks.domain.model.OrdemServico;
import com.company.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servicos")
public class OrdemServicoController {

	private final GestaoOrdemServicoService gestaoOrdemServicoService;
	@Autowired
	private ModelMapper modelMapper;
	
	public OrdemServicoController(GestaoOrdemServicoService gestaoOrdemServicoService) {
		this.gestaoOrdemServicoService = gestaoOrdemServicoService;
	}
	
	@PostMapping
	public OrdemServicoModel geraOrdemServico(@RequestBody @Valid OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toDomain(ordemServicoInput);
		return toModel(gestaoOrdemServicoService.salvar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listarOrdens() {
		return toCollectionModel(gestaoOrdemServicoService.listar());
	}
	
	@GetMapping("/{ordemServicoId}")
	public OrdemServicoModel buscarOrdemPeloID(@PathVariable Long ordemServicoId) {
		OrdemServico ordem = gestaoOrdemServicoService.buscarPeloID(ordemServicoId);
		OrdemServicoModel ordemServicoModel = toModel(ordem);
		return ordemServicoModel;
	}
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void finalizarOrdem(@PathVariable Long ordemServicoId) {
		gestaoOrdemServicoService.finalizar(ordemServicoId);
	}
	
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}
	
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(os -> modelMapper.map(os, OrdemServicoModel.class)).collect(Collectors.toList());
	}
	
	private OrdemServico toDomain(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	
	
}
