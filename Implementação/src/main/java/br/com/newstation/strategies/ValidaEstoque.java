package br.com.newstation.strategies;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaEstoque implements IStrategy {

	@Inject
	private EstoqueDao daoE;
	
	@Override
	public String processar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public void validaEstoque(List<CarrinhoItem> itens) {

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

}
