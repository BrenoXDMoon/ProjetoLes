package br.com.newstation.strategies;

import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Pedido;

public class ValidaQuantidadeProdutos implements IStrategy {

	@Override
	public String processar(EntidadeDominio ent) {
		
		Pedido ped = (Pedido) ent;
		boolean flg = false;

		for(CartaPedido e : ped.getItens()) {
			if(e.getQuantidade().equals(0)) {
				flg = true;
			}
		}
		
		if(flg) {
			return "Um Produto está vazio no carrinho!!!";
		}else {
			return null;
		}
	}
}