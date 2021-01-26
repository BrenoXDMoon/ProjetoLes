package br.com.terrenobenzido.view.command;

import br.com.terrenobenzido.controle.fachada.Fachada;

public abstract class AbstractCommand implements ICommand {

	protected Fachada fachada = new Fachada();

}
