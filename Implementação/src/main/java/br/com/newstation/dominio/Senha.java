package br.com.newstation.dominio;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Senha {
	
	private String senha;
	
	@Transient
	private String confirmaSenha;
	
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
}
