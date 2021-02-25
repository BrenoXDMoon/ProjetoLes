package br.com.newstation.vh;

import br.com.newstation.command.AbstractCommand;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class ListarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return fachada.listar(ent);
	}

}
