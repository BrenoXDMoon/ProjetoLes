package br.com.newstation.commands;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class EditarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return fachada.editar(ent);
	}

}
