package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.command.ListarCommand;
import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.RARIDADE;
import br.com.newstation.dominio.Resultado;

@Model
public class HomeBean {

	@Inject
	private CartaDao dao;
	
	private String busca = "";

	private List<Carta> cartas = new ArrayList<>();

	ListarCommand cmd = new ListarCommand();
	
	public List<Carta> getCartas() {
		
		if (busca.equals(""))
			this.cartas = converteLista(cmd.executar(new Carta()));
		return cartas;
	}

	public void filtraCartas() {

		this.cartas = dao.filtro(busca);
		if (this.cartas == null)
			this.cartas = converteLista(dao.listar(new EntidadeDominio()));
	}

	public String getBusca() {
		return busca;
	}
	
	private List<Carta> converteLista(Resultado listar) {
		
		List<Carta> lista = new ArrayList<Carta>();
		
		for(EntidadeDominio e : listar.getEntidades()) {
			lista.add((Carta) e);
		}
		
		return lista;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}

}
