package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.ExcluirCommand;
import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;

@Model
public class CartaExcluirBean{

	private CartaDao dao = new CartaDao();

	@Transactional
	public String delete(Carta carta) {
		ExcluirCommand cmd = new ExcluirCommand();
		cmd.executar(carta).getEntidade();
//		dao.excluir(carta);
		return "/admin/cartas/lista?faces-redirect=true";
	}
	
}
