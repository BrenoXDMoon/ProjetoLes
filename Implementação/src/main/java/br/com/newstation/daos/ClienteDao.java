package br.com.newstation.daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class ClienteDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) ent;

		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.persist(cliente.getCartoes().toArray()[0]);
		manager.persist(cliente.getEnderecos().toArray()[0]);
		manager.persist(cliente.getDocumentos().toArray()[0]);
		manager.getTransaction().commit();

		resultado.setEntidade(cliente);

		System.out.println("- CLIENTE SALVO COM SUCESSO!!!");
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) ent;
		
		manager.getTransaction().begin();
		
		manager.merge(cliente);
		
		manager.getTransaction().commit();
		manager.close();
		resultado.setEntidade(cliente);
		
		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) ent;

		String jpql = "select distinct(c) from Cliente c where c.id=:id";

		manager.getTransaction().begin();

		Cliente cliFind = manager.createQuery(jpql, Cliente.class).setParameter("id", cliente.getId())
				.getSingleResult();

		cliFind.setAtivo(false);

		manager.getTransaction().commit();
		manager.close();
		resultado.setEntidade(cliente);

		System.out.println("- CLIENTE REMOVIDO COM SUCESSO!!!");
		return resultado;

	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		abrirConexao();

		String jpql = "select distinct(c) from Cliente c join fetch c.documentos  where c.ativo=1";

		Resultado resultado = new Resultado();

		List<Cliente> lista = new ArrayList<Cliente>();

		manager.getTransaction().begin();
		lista = manager.createQuery(jpql, Cliente.class).getResultList();

		manager.getTransaction().commit();

		for (Cliente c : lista) {
			resultado.add(c);
		}

		manager.close();
		factory.close();
		return resultado;
	}

	

	
	public Resultado visualizar(EntidadeDominio ent) {

		abrirConexao();
		
		String jpql = "select distinct(c) from Cliente c join fetch c.documentos where c.id = :id";
		Cliente cli = (Cliente) ent;
		manager.getTransaction().begin();
		Resultado resultado = new Resultado();
		resultado.setEntidade((EntidadeDominio) manager.createQuery(jpql, Cliente.class).setParameter("id", cli.getId())
				.getSingleResult());
		manager.getTransaction().commit();
		manager.close();
		return resultado;
	}

	public Resultado login(EntidadeDominio ent) {

		String jpql = "select distinct(c) from Cliente where c.email = :email, c.senha= :senha join fetch c.enderecos join fetch c.cartoes join fetch c.documentos";
		Cliente cliente = (Cliente) ent;
		Resultado resultado = new Resultado();

		resultado.setEntidade(manager.createQuery(jpql, Cliente.class).setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getSenha().getSenha()).getSingleResult());

		return resultado;
	}
}
