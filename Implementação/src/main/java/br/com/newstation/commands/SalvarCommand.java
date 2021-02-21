package br.com.newstation.commands;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class SalvarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio ent) {
		
		System.out.println("- CHEGOU NA CMD");
		
		return fachada.salvar(ent);
	}
}
