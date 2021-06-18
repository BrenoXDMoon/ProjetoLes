package br.com.newstation.daos;

import java.util.List;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteEndereco;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class EnderecoDao extends AbstractDao{
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		abrirConexao();
		
		Resultado resultado = new Resultado();
//		ClienteEndereco aux = new ClienteEndereco();
		ClienteEndereco aux = (ClienteEndereco) ent;
		Cliente cli = aux.getCliente();
		Endereco end = aux.getEndereco();
		
		manager.getTransaction().begin();
		manager.persist(end);
		manager.merge(cli);
		
		manager.flush();
		manager.getTransaction().commit();
		
		fechaConexao();
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		
		abrirConexao();
		Endereco end = (Endereco) ent;
		
		manager.getTransaction().begin();
		manager.merge(manager.contains(end) ? end : manager.merge(end));
		manager.getTransaction().commit();
		
		fechaConexao();
		
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		
		abrirConexao();
		Resultado resultado = new Resultado();
//		ClienteEndereco aux = new ClienteEndereco();
		ClienteEndereco aux = (ClienteEndereco) ent;
		Cliente cli = aux.getCliente();
		Endereco end = aux.getEndereco();
		
		manager.getTransaction().begin();
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(end) ? end : manager.merge(end));
		manager.getTransaction().commit();
		
		fechaConexao();
		
		return resultado;
		
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		abrirConexao();
		Resultado resultado = new Resultado();
		
		String jpql = "select d from Endereco d where d.ativo = true";
		
		manager.getTransaction().begin();
		List<Endereco> lista = manager.createQuery(jpql, Endereco.class).getResultList();
		manager.getTransaction().commit();
		
		fechaConexao();
		for(Endereco end : lista) {
			resultado.add(end);
		}
		
		return resultado;
	}

	
	public Endereco busca(int id) {
		
		abrirConexao();
		String jpql = "select c from Endereco c where c.id = :id";
		
		manager.getTransaction().begin();
		Endereco endereco =  manager.createQuery(jpql, Endereco.class)
			.setParameter("id", id)
				.getSingleResult();
		manager.getTransaction().commit();
		
		fechaConexao();
		return endereco;
	}	
}
