package br.com.terrenobenzido.view.viewhelper;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.terrenobenzido.modelo.dao.CartaDAO;
import br.com.terrenobenzido.modelo.dominio.Carta;


@Model
public class HomeBean {

	@Inject
	private CartaDAO dao;
	
	public List<Carta> ultimosLancamentos() {
		System.out.println("Entrando nos ultimos lancamentos");
		return dao.ultimosLancamentos();
	}
	
	public List<Carta> demaisLivros() {
		System.out.println("Entrando nos demais livros");
		return dao.demaisCartas();
	}
}
