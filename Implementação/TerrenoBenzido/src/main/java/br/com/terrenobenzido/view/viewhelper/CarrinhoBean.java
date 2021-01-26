package br.com.terrenobenzido.view.viewhelper;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.terrenobenzido.modelo.dao.CartaDAO;
import br.com.terrenobenzido.modelo.dominio.Carrinho;
import br.com.terrenobenzido.modelo.dominio.CarrinhoItem;
import br.com.terrenobenzido.modelo.dominio.Carta;

@Model
public class CarrinhoBean {

	@Inject
	private CartaDAO dao;
	
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
