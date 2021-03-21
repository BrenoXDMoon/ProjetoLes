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
	
	public void editar(Cupom cupom) {
		manager.merge(cupom);
	}
	
	public void excluir() {
		
	}
	
	public List<Cupom> listar(){
		
		return null;
	}
	
}
