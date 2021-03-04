package br.com.newstation.vh;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.TIPO_CLIENTE;

@Model
public class LoginBean {

	private Cliente cliente = new Cliente();
	private static Integer id;
	
	@Inject
	ClienteDao dao;
	
	@Transactional
	public String login() {
		
		this.cliente = dao.login(cliente);
		
		this.id = cliente.getId();
		
		if(this.cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {
			
			return "/admin/admin?faces-redirect=true";
		}else {
			
			return "/cliente/perfil?faces-redirect=true";
		}
	}
	
	public void carregar() {
		
		this.cliente.setId(getId());
		
		this.cliente = dao.visualizar(cliente);
		
		System.out.println("- Carregando o cara na tela");
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
