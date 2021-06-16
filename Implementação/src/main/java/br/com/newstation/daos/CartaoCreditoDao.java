package br.com.newstation.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteAux;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class CartaoCreditoDao implements IDao{

	@PersistenceContext
	private EntityManager manager;

	

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

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		ClienteAux cliAux = (ClienteAux) ent;
		CartaoCredito card = (CartaoCredito) cliAux.getEnt();
		manager.persist(card);
		manager.merge(cliAux.getCliente());
		
		return null;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		
		CartaoCredito card = (CartaoCredito) ent;
		manager.merge(manager.contains(card) ? card : manager.merge(card));
		return null;
	}
	
	@Override
	public Resultado excluir(EntidadeDominio ent) {
		
		ClienteAux cliAux = (ClienteAux) ent;
		CartaoCredito card = (CartaoCredito) cliAux.getEnt();
		Cliente cli = cliAux.getCliente();
		manager.merge(manager.contains(cli ) ? cli : manager.merge(cli));
		manager.remove(manager.contains(card) ? card : manager.merge(card));
		return null;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		String jpql = "select d from CartaoCredito d where d.ativo = true";
		List<CartaoCredito> lista = manager.createQuery(jpql, CartaoCredito.class).getResultList();
		Resultado resultado=new Resultado();
		
		for (CartaoCredito c : lista) {
			resultado.add(c);
		}
		return resultado;
	}

}
