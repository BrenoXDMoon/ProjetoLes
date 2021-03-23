package br.com.newstation.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.newstation.beans.CarrinhoBean;
import br.com.newstation.daos.PedidoDao;

@Named
@SessionScoped
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<CarrinhoItem> itens = new HashSet<>();
 
	@Inject
	private PedidoDao pedidoDao;

	CarrinhoBean cb = new CarrinhoBean();

	public void add(CarrinhoItem item) {
		item.setQuantidadeAnterior(item.getQuantidade());
		itens.add(item);
	}

	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(itens);
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getCarta().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (CarrinhoItem carrinhoItem : itens) {
			total = total
					.add(carrinhoItem.getCarta().getPreco().multiply(new BigDecimal(carrinhoItem.getQuantidade())));
		}

		return total;
	}

	public void remover(CarrinhoItem item) {
		this.itens.remove(item);
	}

	public Integer getQuantidadeTotal() {
		return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}

	public void finalizar(Pedido pedido) {
		pedido.setTotal(getTotal());
		pedidoDao.salvar(pedido);
	}
}
