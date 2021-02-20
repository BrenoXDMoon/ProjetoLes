package br.com.newstation.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class ClienteDao implements IDao{

	private EntityManager manager;
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("newstation");

	@Override
	public Resultado salvar(EntidadeDominio ent) {		
		
		manager = factory.createEntityManager();
		
		System.out.println("- CHEGOU NO DAO");

		Cliente cliente = (Cliente) ent;
		
		
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.persist(cliente.getEnderecos().get(0));
		manager.persist(cliente.getCartoes().get(0));
		
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();


		manager.persist(cliente.getEnderecos());
		Resultado resultado = new Resultado();
		resultado.add(cliente);

		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {

		Cliente cliente = (Cliente) ent;
		manager.merge(cliente);

		Resultado resultado = new Resultado();
		resultado.add(cliente);


		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		Cliente cliente = (Cliente) ent;
		manager.persist(cliente);

		Resultado resultado = new Resultado();
		resultado.add(cliente);


		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		String jpql = "select distinct(c) from Cliente join fetch c.enderecos join fetch c.cartoes";

		Resultado resultado = new Resultado();
		resultado.setEntidades(manager.createQuery(jpql, EntidadeDominio.class).getResultList());

		return resultado;
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

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

}
