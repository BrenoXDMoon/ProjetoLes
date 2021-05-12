package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;

@Model
public class CartaExcluirBean{

	@Inject
	private CartaDao dao;

	@Transactional
	public String delete(Carta carta) {
		dao.delete(carta);
		return "/admin/cartas/lista?faces-redirect=true";
	}
	
}
