package com.company.osworks.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.company.osworks.domain.exception.NegocioException;
import com.company.osworks.domain.model.Cliente;
import com.company.osworks.domain.model.Comentario;
import com.company.osworks.domain.model.OrdemServico;
import com.company.osworks.domain.model.StatusOrdemServico;
import com.company.osworks.domain.repository.ClienteRepository;
import com.company.osworks.domain.repository.ComentarioRepository;
import com.company.osworks.domain.repository.OrdemServicoRepositorio;

@Service
public class GestaoOrdemServicoService {

	private OrdemServicoRepositorio ordemServicoRepositorio;
	private ClienteRepository clienteRepositorio;
	private ComentarioRepository comentarioRepository;
	
	public GestaoOrdemServicoService(OrdemServicoRepositorio ordemServicoRepositorio, ClienteRepository clienteRepositorio, ComentarioRepository comentarioRepository) {
		this.ordemServicoRepositorio = ordemServicoRepositorio;
		this.clienteRepositorio = clienteRepositorio;
		this.comentarioRepository = comentarioRepository;
	}
	
	public OrdemServico salvar(OrdemServico ordemServico) {
		Cliente clienteBuscado = 
				clienteRepositorio.findById(ordemServico.getCliente().getId())
					.orElseThrow(() -> new NegocioException("Cliente nao encontrado"));

		
		ordemServico.setCliente(clienteBuscado);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		return ordemServicoRepositorio.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = 
				ordemServicoRepositorio
					.findById(ordemServicoId)
						.orElseThrow(() -> new EntityNotFoundException("Ordem nao encontrada"));
		
		ordemServico.finalizar();
		ordemServicoRepositorio.save(ordemServico);
	}
	
	public List<OrdemServico> listar(){
		return ordemServicoRepositorio.findAll();
	}
	
	public OrdemServico buscarPeloID(Long ordemServicoId){
		return ordemServicoRepositorio.findById(ordemServicoId).orElseThrow(() -> new EntityNotFoundException("Ordem nao encontrada"));
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		
		OrdemServico ordemServico = ordemServicoRepositorio.findById(ordemServicoId).orElseThrow(() -> new EntityNotFoundException("Ordem nao encontrada"));
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setOrdemServico(ordemServico);
		comentario.setDescricao(descricao);
		
		return comentarioRepository.save(comentario);
	}
	
	public List<Comentario> listarComentarios(Long ordemServicoId) {
		return ordemServicoRepositorio.findById(ordemServicoId).orElseThrow(() -> new EntityNotFoundException("Ordem nao encontrada")).getComentarios();
	}
}
