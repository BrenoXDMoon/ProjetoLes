package br.com.newstation.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.newstation.daos.PedidoDao;

public class Carrinho {

	
private static final long serialVersionUID = 1L;
	
	private Set<CarrinhoItem> itens =
			new HashSet<>();

	@Inject
	private PedidoDao pedidoDao;
	
	public void add(CarrinhoItem item) {
		itens.add(item);
	}

	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(itens);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getCarta().getPreco().multiply(
				new BigDecimal(item.getQuantidade()));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem carrinhoItem : itens) {
			total = total.add(carrinhoItem.getCarta().getPreco()
					.multiply(new BigDecimal(carrinhoItem.getQuantidade())));
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
		pedido.setItens(this.toJson());
		pedido.setTotal(getTotal());
		pedidoDao.salvar(pedido);
	}

	public String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (CarrinhoItem item : itens) {
			builder.add(Json.createObjectBuilder()
					.add("titulo", item.getCarta().getNome())
					.add("preco", item.getCarta().getPreco())
					.add("quantidade", item.getQuantidade())
					.add("total", getTotal(item)));
		}
		String json = builder.build().toString();
		System.out.println(json);
		
		return json;
	}
}
