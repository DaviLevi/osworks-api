package com.company.osworks.api.contract.input;

import javax.validation.constraints.NotBlank;

public class ComentarioInput {

	@NotBlank
	String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
