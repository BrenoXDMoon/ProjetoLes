package br.com.terrenobenzido.modelo.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.modelo.dominio.Pedido;

@Stateful
public class PedidoDAO {
	
	@PersistenceContext
	EntityManager manager;

	public void salvar(Pedido pedido) {
		
		manager.persist(pedido);
		
	}
	
	public void editar(Pedido pedido) {
		manager.merge(pedido);
	}
	
	public void excluir(Pedido pedido) {
		manager.remove(pedido);
	}
	
	public List<Pedido> listar(){
		
		String jpql = "select distinct(p) from Pedido join fetch p.itens join fetch p.cartoes";
		
		return manager.createQuery(jpql, Pedido.class).getResultList();
	}
	
	public List<Pedido> listarByCliente(Cliente cliente){
		
		String jpql = "select distinct(p) from Pedido where p.cliente.id = :id join fetch p.itens join fetch p.cartoes";
		
		return manager.createQuery(jpql, Pedido.class).setParameter("id", cliente.getId()).getResultList();
	}
}
