package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.Resultado;


public class EstoqueDao implements IDao{

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Estoque estoque) {
		manager.persist(estoque);
	}

	public void editar(Estoque estoque) {
		manager.merge(estoque);
	}
	
	public void excluir(Estoque estoque) {
		manager.remove(estoque);
	}
	
	public Estoque update(Estoque estoque) {
		Estoque estoqueFind = manager.getReference(Estoque.class, estoque.getId());
		estoqueFind.setQuantidade(estoque.getQuantidade());
		return estoqueFind;
	}

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}
}
