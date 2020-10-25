package com.company.osworks.api.handler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Problema {

	private int status;
	private LocalDateTime timestamp;
	private String titulo;
	private List<Objeto> objetos;
	
	public Problema() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}
	
	public static class Objeto{
		
		private String propriedade;
		private String detalhe;
		
		public Objeto() {
		}
		
		public String getPropriedade() {return this.propriedade;}
		public String getDetalhe() {return this.detalhe;}

		public void setPropriedade(String propriedade) {
			this.propriedade = propriedade;
		}

		public void setDetalhe(String detalhe) {
			this.detalhe = detalhe;
		}
		
	
	}



	
}
