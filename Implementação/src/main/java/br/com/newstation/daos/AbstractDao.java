package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public abstract class AbstractDao implements IDao {
	
	@PersistenceContext
	protected EntityManager manager;

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		
		manager.remove(ent);
		
		return null;
	}
}
