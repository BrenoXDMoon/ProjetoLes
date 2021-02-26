package br.com.newstation.command;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public interface ICommand {

	Resultado executar(EntidadeDominio ent);
	
}
