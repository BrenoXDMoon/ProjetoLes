package br.com.newstation.dominio;

import java.time.LocalDate;

import javax.persistence.Column;

public class EntidadeDominio {

	@Column
	protected LocalDate dtCadastro;
	
	public LocalDate getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
