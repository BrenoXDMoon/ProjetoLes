package br.com.newstation.converters;

import java.util.HashSet;
import java.util.Set;

import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.CartaoPedido;

public class CartaoPedidoConverter {
	
	public static HashSet<CartaoPedido> converte(Set<CartaoCredito> cartoes){
		
		HashSet<CartaoPedido> cards = new HashSet<CartaoPedido>();
		
		CartaoPedido cdPed = null;
		
		Integer qtd = 0;
		
		for(CartaoCredito cd : cartoes) {
			cdPed = new CartaoPedido();
			cdPed.setCartao(cd);
		}
		
		
		
		return cards;
	}
	
}
