package br.com.terrenobenzido.view.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.terrenobenzido.modelo.dao.PedidoDAO;
import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.modelo.dominio.Pedido;

public class ClientePedidoBean {
	
	private Cliente cliente;

	private Pedido pedido;
	
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	@Inject
	private PedidoDAO dao;
	
	
	public List<Pedido> listarPorCliente(){
		
		return dao.listarByCliente(cliente);
	}
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
