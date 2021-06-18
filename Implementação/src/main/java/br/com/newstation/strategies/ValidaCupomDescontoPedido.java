package br.com.newstation.strategies;

import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaCupomDescontoPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio ent) {

		Cupom cupom = (Cupom) ent;
		if(cupom == null)
			return null;
		else
			return "";

	}
	
}
