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

	private String filtro = "";
	private String busca;
	
	private String[] filtros = {"nome","raridade"};
	
	private List<Carta> cartas = new ArrayList<>();

	
	public List<Carta> getCartasAdmin() {
		return dao.listarAll();
	}
	
	public List<Carta> getCartas() {
		if(filtro.equals(""))
			this.cartas = dao.listar();
		return cartas;
	}
	
	public void filtraCartas() {
		this.cartas = dao.filtro(busca, filtro);
		if(this.cartas == null)
			this.cartas = dao.listar();
	}

	
	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String[] getFiltros() {
		return filtros;
	}

	public void setFiltros(String[] filtros) {
		this.filtros = filtros;
	}
	
	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}
}
