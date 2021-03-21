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
	
	public void salvar() {
		
	}
	
	public void editar() {
		
	}
	
	public void excluir() {
		
	}
	
	public List<Cupom> listar(){
		String jpql = "select C from Cupom C ";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
}
