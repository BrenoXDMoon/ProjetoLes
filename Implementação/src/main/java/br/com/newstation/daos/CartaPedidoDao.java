package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.CartaPedido;

public class CartaPedidoDao {
	
	@PersistenceContext
	private EntityManager manager;

	public void salvar(CartaPedido carta) {
		System.out.println(carta.getCarta().getNome());
		System.out.println(carta.getQuantidade());
		System.out.println(carta.getId());
		manager.persist(carta);
	}

	public void editar(CartaPedido carta) {

		manager.merge(carta);
	}

	

}
