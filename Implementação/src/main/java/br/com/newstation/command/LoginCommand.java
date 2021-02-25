package br.com.newstation.command;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class LoginCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		return fachada.login(ent);
	}

}
