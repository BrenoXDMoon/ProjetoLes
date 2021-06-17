package br.com.newstation.dominio;

public class ClienteEndereco extends EntidadeDominio{
	
	private Cliente cliente;
	private Endereco endereco;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
