package br.com.newstation.beans;

import javax.enterprise.inject.Model;

import br.com.newstation.command.DeleteCommand;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.infra.Log;

@Model
public class ClienteExcluirBean {

	public String excluir(Cliente cli) {
		
		DeleteCommand cmd = new DeleteCommand();
		cmd.executar(cli).getEntidade();
		Log.salvar("Alteração","admin");
		return "/admin/lista?faces-redirect=true";
		
	}
	
}
