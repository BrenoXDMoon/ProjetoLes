package br.com.newstation.dominio;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

public class EntidadeDominio {

	protected LocalDate dtCadastro;
	
	public LocalDate getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
