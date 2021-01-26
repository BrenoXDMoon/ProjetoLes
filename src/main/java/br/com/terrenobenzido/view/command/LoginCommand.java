package br.com.terrenobenzido.view.command;

import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

public class LoginCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		
		System.out.println("- Entrou na LoginCommand");
		return fachada.login(ent);
	}

}
