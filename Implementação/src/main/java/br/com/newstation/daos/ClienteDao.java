package br.com.newstation.daos;

import javax.ejb.Stateful;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class ClienteDao extends AbstractDao{
	
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
		
		abrirConexao();
		
		String jpql = "select distinct(c) from Cliente c join fetch c.cartoes join fetch c.enderecos join fetch c.documentos";
		
		Resultado resultado = new Resultado();
		
		manager.getTransaction().begin();

		for(Cliente c : manager.createQuery(jpql, Cliente.class).getResultList()) {
			resultado.add(c);
		}
		
		manager.getTransaction().commit();
		
		
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
		
		String jpql = "select distinct(c) from Cliente where c.email = :email, c.senha= :senha join fetch c.enderecos join fetch c.cartoes join fetch c.documentos";
		Cliente cliente = (Cliente) ent;
		Resultado resultado = new Resultado();
		
		resultado.setEntidade(manager.createQuery(jpql, Cliente.class)
				.setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getSenha().getSenha())
				.getSingleResult());
		
		return resultado;
	}
}
