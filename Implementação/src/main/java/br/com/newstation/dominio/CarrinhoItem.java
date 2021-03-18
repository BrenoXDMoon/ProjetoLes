package br.com.newstation.dominio;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoItem {

	private Carta carta;
	private Integer quantidade;
	
	public CarrinhoItem(Carta carta) {
		this.carta = carta;
		this.quantidade = 1;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
	
	public List<Integer> estoqueMax(){
		List<Integer> lista = new ArrayList<Integer>();
		for(int i = 1; i <= carta.getEstoque().getQuantidade(); i++) {
			lista.add(i);
		}
		return  lista;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carta == null) ? 0 : carta.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (carta == null) {
			if (other.carta != null)
				return false;
		} else if (!carta.equals(other.carta))
			return false;
		return true;
	}
}
