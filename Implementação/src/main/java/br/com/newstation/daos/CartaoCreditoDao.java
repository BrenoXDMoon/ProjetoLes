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
		
		manager.persist(cliente.getCartoes().toArray()[cliente.getCartoes().size() - 1]);
		manager.merge(cliente);
	}

	public void editar(CartaoCredito doc) {
		
		manager.merge(doc);
		
	}

	public void excluir(CartaoCredito doc) {
		manager.merge(doc);
		
	}

	public List<CartaoCredito> listar(CartaoCredito doc) {
		
		String jpql = "select d from CartaoCredito d where d.ativo = true";
		
		return manager.createQuery(jpql, CartaoCredito.class).getResultList();
	}

}
