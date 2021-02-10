package br.com.newstation.dominio;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa{

	private TipoCliente tipoCliente;
	private String nome;
	private List<Endereco> enderecos;
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
