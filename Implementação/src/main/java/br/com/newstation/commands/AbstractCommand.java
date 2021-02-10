package br.com.newstation.commands;

import br.com.newstation.fachada.Facade;

public abstract class AbstractCommand implements ICommand {

	protected Facade fachada = new Facade();

}
