package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class DocumentoDao implements IDao{

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Cliente cliente, Documento doc) {
		
		manager.persist(doc);
		manager.merge(cliente);
	}

	public void editar(Documento doc) {
		
		manager.merge(manager.contains(doc) ? doc : manager.merge(doc));
		
	}

	public void excluir(Cliente cli,Documento doc) {
		
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(doc) ? doc : manager.merge(doc));
		
	}

	public List<Documento> listar(Cliente cli) {
		
		String jpql = "select d from Documento d where d.ativo = true";
		
		return manager.createQuery(jpql, Documento.class).getResultList();
	}

	
	public Documento busca(int id) {
		String jpql_e = "select c from Documento c where c.id = :id";
		return  manager.createQuery(jpql_e, Documento.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	@Deprecated
	public Resultado salvar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Resultado editar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
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
