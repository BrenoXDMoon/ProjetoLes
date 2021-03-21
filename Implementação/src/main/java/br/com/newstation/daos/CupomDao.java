package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cupom;

@Stateful
public class CupomDao {

	@PersistenceContext
	EntityManager manager;
	
	public void salvar(Cupom cupom) {
		manager.persist(cupom);
	}
	
	public void editar(Cupom cupom) {
		manager.merge(cupom);
	}
	
	public void excluir(Cupom cupom) {
		
	}
	
	public List<Cupom> listar(){
		String jpql = "select C from Cupom C ";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
}
