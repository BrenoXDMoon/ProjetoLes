package br.com.newstation.strategies;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaEstoque implements IStrategy {

	
	@Override
	public String processar(EntidadeDominio ent) {
		System.out.println(ent.getClass().getName()+" entra na Fachada");
		return null;
	}
	
	@Transactional
	public void validaEstoque(List<CarrinhoItem> itens) {

		for (CarrinhoItem item : itens) {
			if (item.getQuantidadeAnterior() < item.getQuantidade()) {
				System.out.println("Retira: "+ item.getQuantidade());
				dropEstoque(item.getCarta(), item.getQuantidade());

			} else if ((item.getQuantidadeAnterior() > item.getQuantidade())) {
				System.out.println("Devolve:" + item.getQuantidadeAnterior());
				devolveEstoque(item.getCarta(), item.getQuantidadeAnterior());

			}
			item.setQuantidadeAnterior(item.getQuantidade());
		}
	}

	@Transactional
	public void dropEstoque(Carta carta, int quantidade) {
		carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() - quantidade);
		EditarCommand cmd = new EditarCommand();
		cmd.executar(carta.getEstoque());
//		daoE.editar(carta.getEstoque());
	}

	@Transactional
	public void devolveEstoque(Carta carta, int quantidade) {
		if(quantidade > 1) {
			carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() + quantidade);
			EditarCommand cmd = new EditarCommand();
			cmd.executar(carta.getEstoque());
		}
//		daoE.update(carta.getEstoque());
	}

}
