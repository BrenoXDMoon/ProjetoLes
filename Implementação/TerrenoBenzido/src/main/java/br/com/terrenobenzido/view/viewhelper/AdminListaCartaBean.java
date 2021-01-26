package br.com.terrenobenzido.view.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.terrenobenzido.modelo.dao.CartaDAO;
import br.com.terrenobenzido.modelo.dominio.Carta;

@Model
public class AdminListaCartaBean {

	@Inject
	private CartaDAO dao;

	private List<Carta> cartas = new ArrayList<>();

	@Transactional
	public String delete(Carta carta) {
		dao.delete(carta);
		return "/cartas/lista?faces-redirect=true";
	}

	@Transactional
	public String Update(Carta carta) {
		dao.update(carta);
		return "/cartas/lista?faces-redirect=true";
	}

	public List<Carta> getCartas() {
		this.cartas = dao.listar();
		return cartas;

	}

}
