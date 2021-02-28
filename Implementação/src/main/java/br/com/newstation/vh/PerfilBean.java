package br.com.newstation.vh;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;

@Model
public class PerfilBean {

	private Integer id;
	
	@Inject
	private ClienteDao dao;
	
	private Cliente cliente  = new Cliente();
	
	private Endereco endereco;
	
	private CartaoCredito cartao;
	
	private Documento doc;
	
	public void Carregar() {
		
		cliente.setId(3);
		cliente =  (Cliente) dao.visualizar(cliente).getEntidade();
//		id = cliente.getId();
		id = 1;
		
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
}
