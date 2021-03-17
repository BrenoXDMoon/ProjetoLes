package br.com.newstation.beans;

import javax.enterprise.inject.Model;

import br.com.newstation.dominio.Endereco;

@Model
public class EnderecoEditarBean {
	
	private Endereco endereco = new Endereco();

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

	
}
