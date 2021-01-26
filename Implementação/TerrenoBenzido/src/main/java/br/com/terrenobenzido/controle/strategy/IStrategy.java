package br.com.terrenobenzido.controle.strategy;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;

public interface IStrategy {

	String processar(EntidadeDominio entidade);

}
