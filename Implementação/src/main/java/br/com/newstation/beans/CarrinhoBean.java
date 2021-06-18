package br.com.newstation.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.Carrinho;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.Carta;
import br.com.newstation.strategies.ValidaEstoque;

@Model
public class CarrinhoBean {

	@Inject
	private CartaDao dao;

	@Inject
	private Carrinho carrinho;


	ValidaEstoque validaestoque = new ValidaEstoque();

	public String add(Integer id) {
		Carta carta = dao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(carta);
		carrinho.add(item);
		
		return "index?faces-redirect=true";
	}

	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}

	@Transactional
	public String redirCheckout(List<CarrinhoItem> itens, boolean logado) {
		if (logado == false) {
			return "/cliente/login?faces-redirect=true";
		}

		validaestoque.validaEstoque(itens);
		return "/checkout/checkout?faces-redirect=true";

	}

	public void carrinhoCheck(List<CarrinhoItem> itens) {
		System.out.println(itens.get(0).getQuantidade());
		validaestoque.validaEstoque(itens);
	}

	@Transactional
	public void remover(CarrinhoItem item) {
		validaestoque.devolveEstoque(item.getCarta(), item.getQuantidadeAnterior());
		carrinho.remover(item);
	}

}
