package br.com.newstation.dominio;

import javax.persistence.Embeddable;

@Embeddable
public class Estado extends EntidadeDominio{
	
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
