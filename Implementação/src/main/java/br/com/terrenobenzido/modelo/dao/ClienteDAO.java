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
		manager.persist(cliente.getEnderecos());
		manager.persist(cliente.getCartoes());

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
		
		manager.remove(cliente);

		Resultado resultado = new Resultado();
		resultado.add(cliente);

		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		String jpql = "select distinct(c) from Cliente c join fetch c.enderecos join fetch c.cartoes";

		Resultado resultado = new Resultado();
		resultado.setEntidades(manager.createQuery(jpql, EntidadeDominio.class).getResultList());

		return resultado;
	}

	@Override
	public Resultado buscarPorId(Integer id) {

		String jpql = "select distinct(c) from Cliente c join fetch c.enderecos join fetch c.cartoes where c.id = :id";

		Cliente cli = new Cliente();
		
		cli = manager.createQuery(jpql, Cliente.class).setParameter("id", id).getSingleResult();

		Resultado resultado = new Resultado();
		resultado.add(cli);

		return resultado;
	}

	public Cliente login(String email, String senha) {
		String jpql = "select distinct(c) from Cliente c join fetch c.enderecos join fetch c.cartoes where c.email = :email and c.senha = :senha";
	
			return manager.createQuery(jpql, Cliente.class).setParameter("email", email).setParameter("senha", senha).getSingleResult();
		
	}
}
