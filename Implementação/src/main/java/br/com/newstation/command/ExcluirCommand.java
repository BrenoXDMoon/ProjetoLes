package br.com.newstation.command;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class ExcluirCommand extends AbstractCommand{

	@Override
	public Resultado executar(EntidadeDominio ent) {
		fachada = this.getFachada(ent);
		return fachada.excluir(ent);
	}

}
