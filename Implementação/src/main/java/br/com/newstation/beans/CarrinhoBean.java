package br.com.newstation.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carrinho;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.Carta;

@Model
public class CarrinhoBean {

	@Inject
	private CartaDao dao;
	
	@Inject
	private Carrinho carrinho;
	
	public String add(Integer id) {
		Carta carta = dao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(carta);
		carrinho.add(item);
		
		return "carrinho?faces-redirect=true";
	}
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
	
	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}
}
