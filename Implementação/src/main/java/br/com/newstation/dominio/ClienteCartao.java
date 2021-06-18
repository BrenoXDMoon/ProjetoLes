package br.com.newstation.dominio;

public class ClienteCartao extends EntidadeDominio {

	private Cliente cliente;
	private CartaoCredito card;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public CartaoCredito getCard() {
		return card;
	}
	public void setCard(CartaoCredito card) {
		this.card = card;
	}	
}
