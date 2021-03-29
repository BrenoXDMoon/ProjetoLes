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
		manager.merge(ped.getCliente());
		manager.persist(ped);
	}

	public void editar(Pedido ped) {
		
		manager.merge(ped);
	}

	public void excluir(Pedido ped) {
		manager.merge(ped);
		
	}
	
	public List<Pedido> listarTudo() {
		
		String jpql = "select p from Pedido p";
		
		return manager.createQuery(jpql, Pedido.class).getResultList();
	}

	public List<Pedido> listar(int cli) {
		
		String jpql = "select p from Pedido p where p.cliente.id = :id";
		
		return manager.createQuery(jpql, Pedido.class).setParameter("id", cli).getResultList();
	}
	
	public Pedido buscarPorId(int ped) {
		
		String jpql = "select p from Pedido p where p.id = :id";
		
		return manager.createQuery(jpql, Pedido.class).setParameter("id", ped).getSingleResult();
	}
}
