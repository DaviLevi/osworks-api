package com.company.osworks.api.contract.input;

import java.math.BigDecimal;

import javax.validation.Valid;

public class OrdemServicoInput {

	private String descricao;
	private BigDecimal preco;
	
	@Valid
	private ClienteIdInput cliente;

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

	public ClienteIdInput getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
	}
	
}
