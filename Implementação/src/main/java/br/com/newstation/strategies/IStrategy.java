package br.com.newstation.strategies;

import br.com.newstation.dominio.EntidadeDominio;

public interface IStrategy {

	String processar(EntidadeDominio ent);
}
