package br.com.newstation.dominio;

public class ClienteDocumento extends EntidadeDominio{

	private Cliente cliente;
	private Documento doc;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Documento getDoc() {
		return doc;
	}
	public void setDoc(Documento doc) {
		this.doc = doc;
	}
}
