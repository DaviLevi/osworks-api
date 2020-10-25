package com.company.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.osworks.api.contract.input.ComentarioInput;
import com.company.osworks.api.contract.model.ComentarioModel;
import com.company.osworks.domain.model.Comentario;
import com.company.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping(path = "/ordem-servicos/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	private final GestaoOrdemServicoService gestaoOrdemServicoService;
	@Autowired
	private ModelMapper modelMapper;

	public ComentarioController(GestaoOrdemServicoService gestaoOrdemServicoService) {
		this.gestaoOrdemServicoService = gestaoOrdemServicoService;
	}
	
	@PutMapping
	public ComentarioModel adicionarComentario(@PathVariable Long ordemServicoId, @RequestBody @Valid ComentarioInput comentarioInput) {
		Comentario comentarioAdicionado = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
		return toModel(comentarioAdicionado);
	}
	
	@GetMapping
	public List<ComentarioModel> listarComentarios(@PathVariable Long ordemServicoId) {
		return toCollectionModel(gestaoOrdemServicoService.listarComentarios(ordemServicoId));
	}
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream().map(c -> modelMapper.map(c, ComentarioModel.class)).collect(Collectors.toList());
		
	}
}
