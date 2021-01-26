package br.com.terrenobenzido.view.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.terrenobenzido.modelo.dao.PedidoDAO;
import br.com.terrenobenzido.modelo.dominio.CartaoCredito;
import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.modelo.dominio.Endereco;
import br.com.terrenobenzido.modelo.dominio.Pedido;


@Model
public class PedidoBean {

	private Cliente cliente;
	private Endereco endereco;
	private List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
	private Pedido pedido;
	
	@Inject
	PedidoDAO dao;
	
	public String editar() {
		
		return "";
	}
	
	public String excluir() {		
		
		return "";
	}
	
	public List<Pedido> listar() {
		
		return null;
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

	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
