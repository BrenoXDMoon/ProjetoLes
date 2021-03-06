package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Estoque;


public class EstoqueDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Estoque estoque) {
		manager.persist(estoque);
	}

	public void editar(Estoque estoque) {
		manager.merge(estoque);
	}
	
	public void excluir(Estoque estoque) {
		manager.remove(estoque);
	}
	
}
