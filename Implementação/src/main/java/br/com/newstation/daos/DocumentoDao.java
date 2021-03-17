package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;

@Stateful
public class DocumentoDao{

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Cliente cliente) {
		
		manager.persist(cliente.getDocumentos().toArray()[0]);
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

}
