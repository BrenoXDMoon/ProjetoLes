package br.com.newstation.converters;

import java.util.HashSet;
import java.util.Set;

import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.CartaoPedido;

public class CartaoPedidoConverter {
	
	HashSet<CartaoPedido> converte(HashSet<CartaoCredito> cartoes){
		
		HashSet<CartaoPedido> cards = new HashSet<CartaoPedido>();
		
		CartaoPedido cdPed = null;
		
		for(CartaoCredito cd : cartoes) {
			cdPed = new CartaoPedido();
			cdPed.setCartao(cd);		
			
		}
		
		return cards;
	}
	
}
