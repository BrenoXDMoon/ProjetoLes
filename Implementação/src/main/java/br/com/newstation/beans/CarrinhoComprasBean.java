package br.com.newstation.beans;

import java.util.List;

import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.CarrinhoCompra;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.Carta;

public class CarrinhoComprasBean {
	
	@Inject
	private CartaDao cartaDao;
	
	@Inject
	private CarrinhoCompra carrinho;

	public String add(Integer id) {
		Carta card = cartaDao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(card);
		carrinho.add(item);

		return "carrinho?faces-redirect=true";
	}

	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}

	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
	
}
