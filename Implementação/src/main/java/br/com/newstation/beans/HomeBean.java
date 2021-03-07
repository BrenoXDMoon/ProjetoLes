package br.com.newstation.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.dominio.Carta;

@Model
public class HomeBean {

	@Inject
	private CartaDao dao;
	
	public List<Carta> ultimosLancamentos() {
		System.out.println("Entrando nos ultimos lancamentos");
		return dao.ultimosLancamentos();
	}
	
	public List<Carta> demaisLivros() {
		System.out.println("Entrando nos demais livros");
		return dao.demaisCartas();
	}
}
