package br.com.newstation.daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.dominio.Senha;
import br.com.newstation.seguranca.CriptografaSenha;

@Stateful
public class ClienteDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) ent;
		
		try {
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.persist(cliente.getEnderecos().toArray()[0]);
			manager.persist(cliente.getDocumentos().toArray()[0]);
			manager.persist(cliente.getCartoes().toArray()[0]);
			
			manager.getTransaction().commit();
			manager.close();
			factory.close();
			
			resultado.setEntidade(cliente);
			
			System.out.println("- CLIENTE SALVO COM SUCESSO!!!");
			return resultado;
			
		}catch(Exception e) {
			
			System.out.println("- ERRO AO SALVAR!!!");
			
			return null;
		}

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
		factory.close();
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
		factory.close();
		resultado.setEntidade(cliente);

		System.out.println("- CLIENTE REMOVIDO COM SUCESSO!!!");
		return resultado;

	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		abrirConexao();

		String jpql = "select distinct(c) from Cliente c ";

		Resultado resultado = new Resultado();

		List<Cliente> lista = new ArrayList<Cliente>();

		manager.getTransaction().begin();
		lista = manager.createQuery(jpql, Cliente.class).getResultList();

		manager.getTransaction().commit();
		manager.close();
		factory.close();

		for (Cliente c : lista) {
			resultado.add(c);
		}
		
		manager.close();
		factory.close();
		return resultado;
	}

	

	
	public Cliente visualizar(EntidadeDominio ent) {

		abrirConexao();
		
		String jpql = "select distinct(c) from Cliente c  where c.id = :id";
		Cliente cli = (Cliente) ent;
		
		System.out.println(cli.getId());
		manager.getTransaction().begin();
		
		cli = manager.createQuery(jpql, Cliente.class).setParameter("id", cli.getId())
				.getSingleResult();
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return cli;
	}

	public Cliente login(EntidadeDominio ent) {

		abrirConexao();
		
		String jpql = "select distinct(c) from Cliente c  where c.email = :email and c.senha= :senha";
		Cliente cliente = (Cliente) ent;
		
		CriptografaSenha crp = new CriptografaSenha();
		Senha senha = new Senha();
		senha.setSenha(crp.criptoSenha(cliente.getSenha().getSenha()));
		cliente.setSenha(senha);
				
		cliente = manager.createQuery(jpql, Cliente.class).setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getSenha()).getSingleResult();

		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return cliente;
	}

	public List<Cliente> listarSemCao() {
		abrirConexao();

		String jpql = "select distinct(c) from Cliente c join fetch c.documentos";

		List<Cliente> lista = new ArrayList<Cliente>();

		manager.getTransaction().begin();
		lista = manager.createQuery(jpql, Cliente.class).getResultList();

		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return lista;
	}
}
