package br.com.newstation.converters;

import java.util.HashSet;

import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.CartaPedido;

public class CartaPedidoConverter {

	HashSet<CartaPedido> converte(HashSet<Carta> cartas){
		
		HashSet<CartaPedido> cartasPed = new HashSet<CartaPedido>();
		
		Integer qtd = 0;
		
		CartaPedido cardPed = null;
		
		for(Carta c:cartas) {
			
			cardPed = new CartaPedido();
			cardPed.setCarta(c);
			
		}
		
		return cartasPed;
	}
	
}
