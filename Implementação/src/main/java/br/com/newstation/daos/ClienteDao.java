package br.com.newstation.daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import org.hibernate.jpa.QueryHints;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class ClienteDao extends AbstractDao{
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		abrirConexao();

		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) ent;
		
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.persist(cliente.getCartoes().get(0));
		manager.persist(cliente.getEnderecos().get(0));
		manager.persist(cliente.getDocumentos().get(0));
		manager.getTransaction().commit();
		
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
		abrirConexao();
		
		String jpql = "select distinct(c) from Cliente c join fetch c.documentos";

		
		Resultado resultado = new Resultado();
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		manager.getTransaction().begin();
		lista = manager.createQuery(jpql, Cliente.class).getResultList();
		
		manager.getTransaction().commit();
		
		for(Cliente c : lista) {
			resultado.add(c);
		}
		
		manager.close();
		factory.close();
		return resultado;
	}
	
	
	
	public Resultado visualizar(EntidadeDominio ent) {
		
		String jpql = "select distinct(c) from Cliente where c.id = :id join fetch c.enderecos join fetch c.cartoes";
		
		Cliente cli = (Cliente) ent;
		
		Resultado resultado = new Resultado();
		resultado.setEntidade((EntidadeDominio)manager
				.createQuery(jpql, Cliente.class)
				.setParameter("id", cli.getId())
				.getSingleResult());
		
		
		return resultado;
	}

	public Resultado login(EntidadeDominio ent) {
		
		String jpql = "select distinct(c) from Cliente c join fetch c.enderecos where c.email = :email and c.senha.senha = :senha";
		String jpql2 ="select distinct(c) from Cliente c join fetch c.documentos where c.email = :email and c.senha.senha = :senha";
		String jpql3 = "select distinct(c) from Cliente c join fetch c.cartoes where c.email = :email and c.senha.senha = :senha";
		
		Cliente cliente = (Cliente) ent;
		Resultado resultado = new Resultado();
		
		Cliente cli = new Cliente();
		
		abrirConexao();
		manager.getTransaction().begin();
		
		cli = manager.createQuery(jpql, Cliente.class)
				.setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getSenha().getSenha())
				.setHint(QueryHints.HINT_FETCH_SIZE, 3)
				.getSingleResult();
		
		cli = manager.createQuery(jpql2, Cliente.class)
				.setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getSenha().getSenha())
				.setHint(QueryHints.HINT_FETCH_SIZE, 3)
				.getSingleResult();
		
		cli = manager.createQuery(jpql3, Cliente.class)
				.setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getSenha().getSenha())
				.setHint(QueryHints.HINT_FETCH_SIZE, 3)
				.getSingleResult();
		
		manager.getTransaction().commit();
		
		return resultado;
	}
}
