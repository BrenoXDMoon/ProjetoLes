package br.com.newstation.vh;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.ICommand;
import br.com.newstation.command.LoginCommand;
import br.com.newstation.dominio.Cliente;

@Model
public class LoginBean {

	private Cliente cliente = new Cliente();
	private Integer id;
	
	private ICommand cmd;
	
	@Transactional
	public void login() {
		cmd = new LoginCommand();
		
		this.cliente = (Cliente) cmd.executar(cliente).getEntidade();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
