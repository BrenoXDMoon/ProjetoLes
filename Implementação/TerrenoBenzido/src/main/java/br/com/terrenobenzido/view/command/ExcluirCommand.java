package br.com.terrenobenzido.view.command;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return fachada.excluir(ent);
	}

}
