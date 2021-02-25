package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class ClienteDao extends AbstractDao{	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {

		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) ent;
		
		manager.persist(cliente);
		manager.persist(cliente.getCartoes().get(0));
		manager.persist(cliente.getEnderecos().get(0));
		manager.persist(cliente.getDocumentos().get(0));
		
		
		resultado.setEntidade(cliente);
		
		System.out.println("- CLIENTE SALVO COM SUCESSO!!!");
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		
		Cliente cliente = (Cliente) ent;
		
		manager.merge(cliente);
		return null;
		
	}



	@Override
	public Resultado excluir(EntidadeDominio ent) {
		return null;
		// TODO Auto-generated method stub
		
	}



	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		return null;
	}
	
	
	
	public Resultado visualizar(EntidadeDominio ent) {
		
		String jpql = "select distinct(c) from Cliente where c.id = :id join fetch c.enderecos join fetch c.cartoes";
		
		Cliente cli = (Cliente) ent;
		
		Resultado resultado = new Resultado();
		resultado.add((EntidadeDominio)manager.createQuery(jpql, Cliente.class)
				.setParameter("id", cli.getId()));
		
		
		return resultado;
	}

	public Resultado login(EntidadeDominio ent) {
		
		String jpql = "select distinct(c) from Cliente where c.email = :email, c.senha= :senha join fetch c.enderecos join fetch c.cartoes";
		Cliente cliente = (Cliente) ent;
		
		
		manager.persist(cliente);
		
		Resultado resultado = new Resultado();
		resultado.add(cliente);
		
		return resultado;
	}
}
