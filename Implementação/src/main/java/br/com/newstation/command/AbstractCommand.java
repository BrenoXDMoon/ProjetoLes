package br.com.newstation.command;

import br.com.newstation.fachada.Fachada;
import br.com.newstation.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();

}
