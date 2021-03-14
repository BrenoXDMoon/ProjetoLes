package br.com.newstation.dominio;

import java.time.LocalDate;

public class EntidadeDominio {

	protected LocalDate dtCadastro;
	
	public LocalDate getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
