package br.com.newstation;

import br.com.newstation.dominio.Carta;

public class CartaPedido {

	private Carta carta;
	private Integer quantidade;
	
	public Carta getCarta() {
		return carta;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
