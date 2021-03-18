package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Carta;

@Stateful
public class CartaDao{

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Carta carta) {
		manager.persist(carta);
	}

	public void editar(Carta carta) {

		manager.merge(carta);
	}
	
	public void delete(Carta carta) {
		Carta cartaDelete = manager.getReference(Carta.class, carta.getId());
		cartaDelete.setAtivo(false);
	}
	
	
	public List<Carta> listar() {
		
		String jpql = "select C from Carta C where C.ativo=true";
				
		return manager.createQuery(jpql, Carta.class).getResultList();
	}

	public List<Carta> demaisCartas() {
		String jpql = "select c from Carta c order by c.id desc";
		return manager.createQuery(jpql, Carta.class)
				.setFirstResult(5)
				.getResultList();
	}

	public List<Carta> ultimosLancamentos() {
		String jpql = "select c from Carta c order by c.id desc";
		return manager.createQuery(jpql, Carta.class)
				.setMaxResults(5)
				.getResultList();
	}

	public Carta buscarPorId(Integer id) {
		String jpql = "select c from Carta c where c.id = :id";
		return manager.createQuery(jpql, Carta.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
