package br.com.terrenobenzido.view.command;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

public interface ICommand {
	
	Resultado executar(EntidadeDominio ent);
}
