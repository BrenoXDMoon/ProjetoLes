package br.com.newstation.strategies;

import br.com.newstation.dominio.EntidadeDominio;

public class ValidacaoEntidadeNula implements IStrategy {

	@Override
	public String processar(EntidadeDominio ent) {

		if(ent == null) {
			return"Objeto está nulo!!";
		}else {
			return null;
		}
	}

}
