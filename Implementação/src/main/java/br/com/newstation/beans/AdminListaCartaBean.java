package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.RARIDADE;

@Model
public class AdminListaCartaBean {

	@Inject
	private CartaDao dao;

	private List<Carta> cartas = new ArrayList<>();

	public List<Carta> getCartasAdmin() {
		return dao.listarAll();
	}

	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}
}
