package br.com.newstation.dominio;

import javax.persistence.Embeddable;

@Embeddable
public class TipoCliente extends EntidadeDominio {
	
	private String tipoCliente;
	private String descricao;
	
	public String gettipoCliente() {
		return tipoCliente;
	}
	public void settipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
