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
	
}
