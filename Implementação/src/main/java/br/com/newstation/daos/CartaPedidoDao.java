package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class CartaPedidoDao implements IDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		CartaPedido carta = (CartaPedido) ent;
		System.out.println(carta.getCarta().getNome());
		System.out.println(carta.getQuantidade());
		System.out.println(carta.getId());
		manager.persist(carta);
		return null;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		CartaPedido carta = (CartaPedido) ent;
		manager.merge(carta);
		return null;
	}

	@Override
	@Deprecated
	public Resultado excluir(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

}
