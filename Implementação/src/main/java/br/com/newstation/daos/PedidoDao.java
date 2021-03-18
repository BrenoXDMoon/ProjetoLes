package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Pedido;

@Stateful
public class PedidoDao {

	@PersistenceContext	
	private EntityManager manager;
	
	public void salvar(Pedido ped) {
		
		manager.persist(ped);
	}

	public void editar(Pedido ped) {
		
		manager.merge(ped);
	}

	public void excluir(Pedido ped) {
		manager.merge(ped);
		
	}

	public List<Pedido> listar(Pedido ped) {
		
		String jpql = "select p from Pedido p where p.ativo = true";
		
		return manager.createQuery(jpql, Pedido.class).getResultList();
	}
	
	public Pedido buscarPorId(int ped) {
		
		String jpql = "select p from Pedido p where p.id = :id";
		
		return manager.createQuery(jpql, Pedido.class).setParameter("id", ped).getSingleResult();
	}

}
