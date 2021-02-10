package br.com.newstation.daos;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class ClienteDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		Cliente cliente = (Cliente) ent;
		manager.persist(cliente);
		manager.persist(cliente.getEnderecos().get(0));

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
	public Resultado listar(EntidadeDominio ent) {
		String jpql = "select distinct(c) from Cliente join fetch c.enderecos join fetch c.cartoes";

		Resultado resultado = new Resultado();
		resultado.setEntidades(manager.createQuery(jpql, EntidadeDominio.class).getResultList());

		return resultado;
	}

	public Resultado buscarPorId(Integer id) {

		String jpql = "select distinct(c) from Cliente c join fetch c.enderecos join fetch c.cartoes where c.id = :id";


		Cliente cli = new Cliente();
		
		cli = manager.createQuery(jpql, Cliente.class).setParameter("id", id).getSingleResult();

		Resultado resultado = new Resultado();
		resultado.add(cli);

		return resultado;
	}

//	public Cliente login(Cliente user) {
//		String jpql = "select distinct(c) from Cliente c where c.email = :email and c.senha= :senha";
//
//		try {
//			return manager.createQuery(jpql, Cliente.class).setParameter("email", user.getEmail())
//					.setParameter("senha", user.getSenha()).getSingleResult();
//		} catch (Exception e) {
//			return null;
//		}
//	}

	public Cliente procuraEmail(String email) {
		String jpql = "select distinct(c) from Cliente c where c.email = :email";

		return manager.createQuery(jpql, Cliente.class).setParameter("email", email).getSingleResult();
	}

}
