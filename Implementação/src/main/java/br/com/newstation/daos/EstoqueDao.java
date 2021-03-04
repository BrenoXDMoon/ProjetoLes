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
	
	public Estoque update(Estoque estoque) {
		Estoque estoqueFind = manager.getReference(Estoque.class, estoque.getId());
		estoqueFind.setQuantidade(estoque.getQuantidade());
		return estoqueFind;
	}
}
