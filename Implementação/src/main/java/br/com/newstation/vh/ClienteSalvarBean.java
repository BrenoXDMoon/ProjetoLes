package br.com.newstation.vh;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.commands.ICommand;
import br.com.newstation.commands.SalvarCommand;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cidade;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Estado;
import br.com.newstation.dominio.Senha;
import br.com.newstation.dominio.TIPO_ENDERECO;

@Model
public class ClienteSalvarBean {

	private ICommand cmd;
	
	private Cliente cliente = new Cliente();
	
	private Endereco endereco = new Endereco();
	
	private CartaoCredito cartao = new CartaoCredito();
	
	private Senha senha = new Senha();
	
	private Cidade cidade = new Cidade();
	
	private Estado estado = new Estado();
	
	
	
	@Transactional
	public String salvar(){
		
		System.out.println("- CHEGOU NA BEAN");
		System.out.println(" senha:" + senha.getSenha() + " |confirma senha:" + senha.getConfirmaSenha());
//		if(senha.getSenha() == senha.getConfirmaSenha()) {
			
		System.out.println("- SENHA VALIDADA NA BEAN");
		
		cmd = new SalvarCommand();
		
		cliente.setSenha(senha);
		
		cidade.setEstado(estado);
		
		endereco.setCidade(cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getCartoes().add(cartao);
		
		cmd.executar(cliente);
		
		return "/cliente/perfil.xhtml?faces-redirect=true";			
//		}else {
//			return "";
//		}
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
	
	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}
	
	public Cidade getCidade() {
		return cidade;
	}




	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}




	public Estado getEstado() {
		return estado;
	}




	public void setEstado(Estado estado) {
		this.estado = estado;
	}




	public TIPO_ENDERECO[] getTipos() {
		return TIPO_ENDERECO.values();
	}

	public BANDEIRA[] getBandeiras() {
		return BANDEIRA.values();
	}
}
