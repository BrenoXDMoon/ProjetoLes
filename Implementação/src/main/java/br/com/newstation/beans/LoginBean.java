package br.com.newstation.beans;

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
	private static boolean statusSessao;
	
	@Inject
	ClienteDao dao;
	
	public LoginBean() {
		setStatusSessao(false);
	}
	
	@Transactional
	public String login() {
		
		try {
			this.cliente = dao.login(cliente);
			
			this.id = cliente.getId();
			
			this.setStatusSessao(true);
			
			if(this.cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {
				
				return "/admin/admin?faces-redirect=true";
			}else {
				
				return "/cliente/perfil?faces-redirect=true";
			}
			
		}catch (Exception e) {
			return "/cliente/login?faces-redirect=true";
		}
		
	}
	
	@Transactional
	public String logout() {
		
		this.cliente = new Cliente();
		this.setStatusSessao(false);
		return "/cliente/login?faces-redirect=true";
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
	
	public boolean listaVazia() {
		
		boolean as;
		
		if(this.cliente.getCartoes().isEmpty()) {
			as = true;
		}else {
			as = false;
		}
		
		return as;
	}

	public static boolean isStatusSessao() {
		return statusSessao;
	}

	public static void setStatusSessao(boolean statusSessao) {
		LoginBean.statusSessao = statusSessao;
	}
	
}
