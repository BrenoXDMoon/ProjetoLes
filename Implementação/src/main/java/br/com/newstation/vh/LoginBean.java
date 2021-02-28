package br.com.newstation.vh;

import java.io.IOException;

import javax.enterprise.inject.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.newstation.command.ICommand;
import br.com.newstation.command.LoginCommand;
import br.com.newstation.dominio.Cliente;

import br.com.newstation.dominio.TIPO_CLIENTE;

@Model
public class LoginBean {

	private Cliente cliente = new Cliente();
	
	private Senha senha = new Senha();
	
	private Integer id;
	
	private ICommand cmd;
	
	@Transactional

	public String login() {
		cmd = new LoginCommand();
		
		cliente.setSenha(senha);
		
		this.cliente = (Cliente) cmd.executar(cliente).getEntidade();
		
		if(this.cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {
			
			return "/admin/admin?faces-redirect=true";
			
		}else {
			
			return "/cliente/perfil?faces-redirect=true";
			
		}
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
