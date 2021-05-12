package br.com.newstation.beans;

import javax.enterprise.inject.Model;

import br.com.newstation.command.DeleteCommand;
import br.com.newstation.dominio.Cliente;

@Model
public class ClienteExcluirBean {

	public String excluir(Cliente cli) {
		
		DeleteCommand cmd = new DeleteCommand();
		cmd.executar(cli).getEntidade();
		return "/admin/lista?faces-redirect=true";
		
	}
	 
}
