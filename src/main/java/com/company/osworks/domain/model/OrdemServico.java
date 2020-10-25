package com.company.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.company.osworks.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	private String descricao;
	
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private StatusOrdemServico status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@OneToMany(mappedBy = "ordemServico")
	List<Comentario> comentarios = new ArrayList<>();
	
	public OrdemServico() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public StatusOrdemServico getStatus() {
		return status;
	}


	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}


	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}


	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	public boolean podeSerFinalizado() {
		return StatusOrdemServico.ABERTA.equals(getStatus());
	}
	
	public boolean naoPodeSerFinalizado() {
		return ! StatusOrdemServico.ABERTA.equals(getStatus());
	}
	
	public void finalizar() {
		if(naoPodeSerFinalizado()) 
			throw new NegocioException("A ordem nao pode ser finalizada");
		this.status = StatusOrdemServico.FINALIZADA;
	}
	
}
