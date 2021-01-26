package br.com.terrenobenzido.view.viewhelper;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.terrenobenzido.modelo.dao.CartaDAO;
import br.com.terrenobenzido.modelo.dominio.Carta;

@Model
public class CartaDetalheBean {

	@Inject
	private CartaDAO dao;
	private Carta carta;
	private int id;

	public void carregaDetalhe() {
		this.setCarta(dao.buscarPorId(id));
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
