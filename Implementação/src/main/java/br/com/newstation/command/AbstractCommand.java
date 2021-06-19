package br.com.newstation.command;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.fachada.IFachada;
import br.com.newstation.fachadagenerator.GeradorFachada;

public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada;
	
	IFachada getFachada(EntidadeDominio ent){
		
		GeradorFachada grd = new GeradorFachada();
		
		return grd.retornaFachada(ent);
	}

}
