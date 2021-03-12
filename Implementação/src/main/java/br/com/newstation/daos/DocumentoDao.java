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
		
		manager.merge(doc);
		
	}

	public void excluir(Documento doc) {
		manager.merge(doc);
		
	}

	public List<Documento> listar(Documento doc) {
		
		String jpql = "select d from Documento d where d.ativo = true";
		
		return manager.createQuery(jpql, Documento.class).getResultList();
	}

}
