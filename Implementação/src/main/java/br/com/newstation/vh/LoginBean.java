package br.com.newstation.vh;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.ICommand;
import br.com.newstation.command.LoginCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Senha;
import br.com.newstation.dominio.TIPO_CLIENTE;

@Model
public class LoginBean {

	@Inject
	private Cliente cliente;
	
	@Inject
	private ClienteDao dao;
	
	private Senha senha = new Senha();
	
	private static Integer id;
	
	private ICommand cmd;
	
	private Endereco endereco;
	
	private CartaoCredito cartao;
	
	private Documento doc;
	
	@Transactional

	public String login() {
		cmd = new LoginCommand();
		
		cliente.setSenha(senha);
		
		setCliente((Cliente) cmd.executar(cliente).getEntidade());
		
		setId(cliente.getId());
		
		try {
			if(getCliente().getTipoCliente().equals(TIPO_CLIENTE.Admin)) {
				
				return "/admin/admin?faces-redirect=true";
				
			}else {
				
				return "/cliente/perfil?faces-redirect=true";
				
			}
		}catch (Exception e) {
			return "/erro/erro?faces-redirect=true";
		}
		
		
	}
	
	@Transactional
	public void Carregar() {
		cliente.setId(getId());
		cliente =  (Cliente) dao.visualizar(cliente).getEntidade();
		
		setEndereco((Endereco) cliente.getEnderecos().toArray()[0]);
		
		Object[] array_cc = cliente.getCartoes().toArray();
		cartao = (CartaoCredito) array_cc[0];
		
		Object[] array_doc = cliente.getDocumentos().toArray();
		doc = (Documento) array_doc[0];
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
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
