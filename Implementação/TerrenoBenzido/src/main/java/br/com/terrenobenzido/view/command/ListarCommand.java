package br.com.terrenobenzido.view.command;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

public class ListarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		
		return fachada.listar(ent);
	}

}
