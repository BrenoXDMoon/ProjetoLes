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

@Model
public class CarrinhoBean {

	@Inject
	private CartaDao dao;

	@Inject
	private EstoqueDao daoE;

	@Inject
	private Carrinho carrinho;
	
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

		validaEstoque(itens);
		return "/checkout/checkout?faces-redirect=true";

	}

	@Transactional
	public void validaEstoque(List<CarrinhoItem> itens) {
		System.out.println("itens:" + itens.size());

		for (CarrinhoItem item : itens) {
			if (item.getQuantidadeAnterior() < item.getQuantidade()) {
				dropEstoque(item.getCarta(), item.getQuantidade());

			} else if ((item.getQuantidadeAnterior() > item.getQuantidade())) {
				devolveEstoque(item.getCarta(), item.getQuantidadeAnterior());

			}
			item.setQuantidadeAnterior(item.getQuantidade());
		}
	}

	@Transactional
	public void dropEstoque(Carta carta, int quantidade) {
		carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() - quantidade);
		daoE.editar(carta.getEstoque());
	}

	@Transactional
	public void devolveEstoque(Carta carta, int quantidade) {
		carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() + quantidade);
		daoE.editar(carta.getEstoque());
	}

	@Transactional
	public void remover(CarrinhoItem item) {
		devolveEstoque(item.getCarta(), item.getQuantidadeAnterior());
		carrinho.remover(item);
	}



}
