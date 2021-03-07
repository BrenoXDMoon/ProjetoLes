package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;


@Model
public class CartaDetalheBean {
	
	@Inject
	private CartaDao dao;
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
