package br.com.newstation.dominio;

import javax.persistence.Embeddable;

@Embeddable
public class TipoEndereco extends EntidadeDominio {
	
	private String tipoEndereco;
	
	public String getNome() {
		return tipoEndereco;
	}
	public void setNome(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
}
