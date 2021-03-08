package br.com.newstation.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Endereco;

public class EnderecoDao{

	@PersistenceContext	
	private EntityManager manager;
	
	public void salvar(Cliente cli) {
		
		manager.persist(cli.getEnderecos().toArray()[0]);
	}

	public void editar(Cliente cli) {
		
		manager.merge(cli.getEnderecos().toArray()[0]);
		
	}

	public void excluir(Cliente cli) {
		manager.merge(cli.getEnderecos().toArray()[0]);
		
	}

	public List<Endereco> listar(Cliente cli) {
		
		String jpql = "select d from Endereco d where d.ativo = true";
		
		return manager.createQuery(jpql, Endereco.class).getResultList();
	}

}
