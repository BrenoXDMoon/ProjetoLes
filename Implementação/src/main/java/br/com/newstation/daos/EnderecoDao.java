package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Endereco;

@Stateful
public class EnderecoDao{

	@PersistenceContext	
	private EntityManager manager;
	
	public void salvar(Cliente cli,Endereco end) {

		manager.persist(end);
		manager.merge(cli);
		
	}

	public void editar(Endereco end) {
		
		manager.merge(manager.contains(end) ? end : manager.merge(end));
		
	}

	public void excluir(Cliente cli,Endereco end) {
		
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(end) ? end : manager.merge(end));
		
	}

	public List<Endereco> listar(Cliente cli) {
		
		String jpql = "select d from Endereco d where d.ativo = true";
		
		return manager.createQuery(jpql, Endereco.class).getResultList();
	}

	
	public Endereco busca(int id) {
		String jpql_e = "select c from endereco c where c.id = :id";
		return  manager.createQuery(jpql_e, Endereco.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
