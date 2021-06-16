package br.com.newstation.dominio;

public class ClienteAux extends EntidadeDominio{

	private Cliente cliente;
	private EntidadeDominio ent;
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public EntidadeDominio getEnt() {
		return ent;
	}
	public void setEnt(EntidadeDominio ent) {
		this.ent = ent;
	}
}
