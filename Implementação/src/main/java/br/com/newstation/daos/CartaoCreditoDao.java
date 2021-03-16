package br.com.newstation.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;

public class CartaoCreditoDao{

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Cliente cliente) {
		
		manager.persist(cliente.getCartoes().toArray()[0]);
		manager.merge(cliente);
	}

	public void editar(CartaoCredito card) {
		
		manager.merge(manager.contains(card) ? card : manager.merge(card));
		
	}

	public void excluir(Cliente cli,CartaoCredito card) {
		
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(card) ? card : manager.merge(card));
		
	}

	public List<CartaoCredito> listar(Cliente cli) {
		
		String jpql = "select d from CartaoCredito d where d.ativo = true";
		
		return manager.createQuery(jpql, CartaoCredito.class).getResultList();
	}

	
	public CartaoCredito busca(int id) {
		String jpql_e = "select c from CartaoCredito c where c.id = :id";
		return  manager.createQuery(jpql_e, CartaoCredito.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
