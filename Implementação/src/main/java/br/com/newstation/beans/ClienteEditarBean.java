package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Senha;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.seguranca.CriptografaSenha;

@Model
public class ClienteEditarBean {

	private Senha senha = new Senha();

	private static Cliente cliente = new Cliente();
	
	CriptografaSenha crp = new CriptografaSenha();
	
	private Integer id;
	
	public void carregaDetalhe() {
		
		ClienteDao dao = new ClienteDao();
		cliente =  dao.visualizar(cliente);
		
		//senha = cliente.getSenha();
	}
	
	@Transactional
	public String editar() {
		
		if (senha != null) {
			
			senha.setSenha(crp.criptoSenha(senha.getSenha()));
			cliente.setSenha(senha);
			
		}


		EditarCommand cmd = new EditarCommand();
		cmd.executar(cliente);
		
		return "/admin/cliente/lista?faces-redirect=true";
	}

	public String redir() {
		return "/cliente/editar?faces-redirect=true";
	}
	
	public String redirAdmin(Cliente cli) {
		
		ClienteEditarBean.cliente = cli;
		
		return "/admin/cliente/editar?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {

		ClienteEditarBean.cliente = cliente;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public TIPO_CLIENTE[] getTipoCliente(){
		
		return TIPO_CLIENTE.values();
	}
}
