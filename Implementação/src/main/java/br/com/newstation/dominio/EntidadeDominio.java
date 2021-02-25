package br.com.newstation.dominio;

import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EntidadeDominio {

	@Temporal(TemporalType.DATE)
	protected LocalDate dtCadastro;
	
	public LocalDate getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
