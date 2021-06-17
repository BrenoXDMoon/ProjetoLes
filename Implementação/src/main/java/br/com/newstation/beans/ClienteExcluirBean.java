package br.com.newstation.beans;

import javax.enterprise.inject.Model;

import br.com.newstation.command.ExcluirCommand;
import br.com.newstation.dominio.Cliente;

@Model
public class ClienteExcluirBean {

	public String excluir(Cliente cli) {
		
		ExcluirCommand cmd = new ExcluirCommand();
		cmd.executar(cli).getEntidade();
		return "/admin/lista?faces-redirect=true";
		
	}
	 
}
