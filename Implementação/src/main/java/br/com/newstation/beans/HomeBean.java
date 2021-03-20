package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.RARIDADE;

@Model
public class HomeBean {

	@Inject
	private CartaDao dao;
	private String busca = "";

	private List<Carta> cartas = new ArrayList<>();

	public List<Carta> getCartas() {
		if (busca.equals(""))
			this.cartas = dao.listar();
		return cartas;
	}

	public void filtraCartas() {

		this.cartas = dao.filtro(busca);
		if (this.cartas == null)
			this.cartas = dao.listar();
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}
}
