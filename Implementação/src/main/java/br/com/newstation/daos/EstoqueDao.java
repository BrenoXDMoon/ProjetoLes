package br.com.newstation.daos;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Estoque;

@Stateful
public class EstoqueDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Deprecated
	public EstoqueDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void salvar(Estoque estoque) {
		manager.persist(estoque);
	}
	
}
