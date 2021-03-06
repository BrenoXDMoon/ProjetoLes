package br.com.newstation.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Endereco;

public class EnderecoDao{

	@PersistenceContext	
	private EntityManager manager;
	
	public void salvar(Endereco doc) {
		
		manager.persist(doc);
	}

	public void editar(Endereco doc) {
		
		manager.merge(doc);
		
	}

	public void excluir(Endereco doc) {
		manager.merge(doc);
		
	}

	public List<Endereco> listar(Endereco doc) {
		
		String jpql = "select d from Endereco d where d.ativo = true";
		
		return manager.createQuery(jpql, Endereco.class).getResultList();
	}

}
