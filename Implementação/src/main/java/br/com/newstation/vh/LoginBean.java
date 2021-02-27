package br.com.newstation.vh;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import br.com.newstation.command.ICommand;
import br.com.newstation.command.LoginCommand;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Senha;

@Model
public class LoginBean {

	private Cliente cliente = new Cliente();
	
	private Senha senha = new Senha();
	
	private Integer id;
	
	private ICommand cmd;
	
	@Transactional
	public void login() throws IOException {
		cmd = new LoginCommand();
		
		cliente.setSenha(senha);
		
		this.cliente = (Cliente) cmd.executar(cliente).getEntidade();
		
		FacesContext fContext = FacesContext.getCurrentInstance();
		ExternalContext extContext = fContext.getExternalContext();
		extContext.redirect(extContext.getRequestContextPath() + "/cliente/perfil.html");
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

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}
	
}
