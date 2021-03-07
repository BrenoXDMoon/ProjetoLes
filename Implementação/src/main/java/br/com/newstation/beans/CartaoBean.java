package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.dominio.CartaoCredito;

@Model
public class CartaoBean {
	
	@Inject
	private CartaoCreditoDao dao;
	
	private CartaoCredito cartao = new CartaoCredito();

	
	@Transactional
	public String salvar() {
		
		dao.salvar(cartao);
		
		return "cliente/perfil?faces-redirect=true";
	}
	
	@Transactional
	public String editar() {
		
		
		dao.editar(getCartao());
		
		return "cliente/perfil?faces-redirect=true";
	}
	
	@Transactional
	public String excluir(CartaoCredito card) {
		
		dao.excluir(card);
		
		return "cliente/perfil?faces-redirect=true";
	}
	
	
	public String redirEditar(CartaoCredito card){
		
		this.cartao = card;
		return "cliente/cartao/form?faces-redirect=true";
		
	}

	public CartaoCredito getCartao() {
		return cartao;
	}
	
	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}
}
