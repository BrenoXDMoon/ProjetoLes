package br.com.terrenobenzido.modelo.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;

@Stateful
public class ClienteDAO extends AbstractDAO {

	@PersistenceContext
	protected EntityManager manager;

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		Cliente cliente = (Cliente) ent;
		manager.persist(cliente);
		manager.persist(cliente.getEnderecos().get(0));
		manager.persist(cliente.getCartoes().get(0));

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

	@Override
	public Resultado visualizar(EntidadeDominio ent) {

		String jpql = "select distinct(c) from Cliente where c.id = :id join fetch c.enderecos join fetch c.cartoes";

		Cliente cli = (Cliente) ent;

		Resultado resultado = new Resultado();
		resultado.add((EntidadeDominio) manager.createQuery(jpql, Cliente.class).setParameter("id", cli.getId()));

		return resultado;
	}

	public Resultado login(EntidadeDominio ent) {

		Cliente cliente = (Cliente) ent;

		Resultado resultado = new Resultado();
		resultado.add(cliente);

		return resultado;
	}

	public Cliente login(Cliente user) {
		String jpql = "select distinct(c) from Cliente c where c.email = :email and c.senha= :senha";

		try {
			return manager.createQuery(jpql, Cliente.class).setParameter("email", user.getEmail())
					.setParameter("senha", user.getSenha()).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Cliente procuraEmail(String email) {
		String jpql = "select distinct(c) from Cliente c where c.email = :email";

		return manager.createQuery(jpql, Cliente.class).setParameter("email", email).getSingleResult();
	}

}
