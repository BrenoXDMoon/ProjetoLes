package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.Resultado;

public class EstoqueDao implements IDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		Estoque estoque = (Estoque) ent;
		Resultado res = new Resultado();
		manager.persist(estoque);
		return res;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		Resultado res = new Resultado();
		Estoque estoque = (Estoque) ent;
		manager.merge(estoque);
		return res;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		Resultado res = new Resultado();
		Estoque estoque = (Estoque) ent;
		manager.remove(estoque);
		return res;
	}

	public Estoque update(Estoque estoque) {
		Estoque estoqueFind = manager.getReference(Estoque.class, estoque.getId());
		estoqueFind.setQuantidade(estoque.getQuantidade());
		return estoqueFind;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}
}
