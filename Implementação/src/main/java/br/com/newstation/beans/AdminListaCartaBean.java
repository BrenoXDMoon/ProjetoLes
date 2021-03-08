package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;

@Model
public class AdminListaCartaBean {

	@Inject
	private CartaDao dao;

	private List<Carta> cartas = new ArrayList<>();

	public List<Carta> getCartas() {
		this.cartas = dao.listar();
		return cartas;

	}
}
