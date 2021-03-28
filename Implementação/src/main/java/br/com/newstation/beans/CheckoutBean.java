package br.com.newstation.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.CupomDao;
import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.Carrinho;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.STATUS_PEDIDO;

@Model
public class CheckoutBean {

	@Inject
	ClienteDao dao;

	@Inject
	CupomDao cDao;
	
	@Inject
	EnderecoDao eDao;
	
	@Inject
	CartaoCreditoDao ccDao;

	@Inject
	PedidoDao pDao;
	
	private Cupom cupom = new Cupom();

	private Endereco end = new Endereco();
	
	private Integer idCli;
	
	
	private Set<Endereco> enderecos = new HashSet<Endereco>();

	private Cliente cliente = new Cliente();

	private Set<CartaoCredito> cartoes = new HashSet<CartaoCredito>();

	private Pedido pedido = new Pedido();

	public Set<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setCartoes(Set<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void salvar(Integer id, BigDecimal total, Carrinho carrinho) {
		
		Cliente cli = new Cliente(); 
		
		cli.setId(id);
		
		pedido.setCliente(dao.visualizar(cli));
		pedido.setCupomDesconto(cDao.buscarById(cupom.getId()));
		pedido.setEndereco(eDao.busca(end.getId()));
		pedido.setCartoes(cartoes);
		pedido.setTotal(total);
		for(CarrinhoItem c: carrinho.getItens()) {
			for(int i = c.getQuantidade(); i > 0 ; i--) {
				pedido.getItens().add(c.getCarta());
			}
		}
		
		pedido.setStatusPedido(STATUS_PEDIDO.Pendente);
		pDao.salvar(pedido);
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getIdCli() {
		return idCli;
	}

	public void setIdCli(Integer idCli) {
		this.idCli = idCli;
	}
	
	@Transactional
	public void setIdCliente(Integer i) {
		setIdCli(i);
	}

}
