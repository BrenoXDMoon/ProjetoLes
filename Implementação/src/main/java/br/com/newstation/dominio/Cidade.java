package br.com.newstation.dominio;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Cidade extends EntidadeDominio{

	private String cidade;
	
	@Embedded
	private Estado estado;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
