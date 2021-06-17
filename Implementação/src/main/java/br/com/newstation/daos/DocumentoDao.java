package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteDocumento;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class DocumentoDao extends AbstractDao{
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		abrirConexao();
		
		ClienteDocumento cliAux = (ClienteDocumento)ent;
		Documento doc = (Documento) cliAux.getEnt();
		Resultado resultado = new Resultado();
		
		
		try {
			manager.getTransaction().begin();
			manager.persist(doc );
			manager.merge(cliAux.getCliente());
			
			manager.flush();
			manager.getTransaction().commit();
			
			resultado.setEntidade(doc);
			
		}catch(Exception e) {
			
			System.out.println("- ERRO AO SALVAR!!!");
			resultado.setMensagem("- ERRO AO SALVAR!!!");
			
		}finally {
			fechaConexao();
		}
		
		return resultado;
	}
	
	@Override
	public Resultado editar(EntidadeDominio ent) {
			
		abrirConexao();
		
		Documento doc = (Documento) ent;
		Resultado resultado = new Resultado();
		
		try {
		manager.getTransaction().begin();
		manager.merge(manager.contains(doc) ? doc : manager.merge(doc));
		manager.flush();
		manager.getTransaction().commit();
		}catch(Exception e) {
		
		System.out.println("- ERRO AO SALVAR!!!");
		resultado.setMensagem("- ERRO AO SALVAR!!!");
		
		}finally {
			fechaConexao();
		}
	
		return resultado;
	}
	
	@Override
	public Resultado excluir(EntidadeDominio ent) {

		abrirConexao();
		
		ClienteDocumento cliAux = (ClienteDocumento)ent;
		Cliente cli = cliAux.getCliente();
		Documento doc = (Documento) cliAux.getEnt();
		
		try {
			manager.getTransaction().begin();
			manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
			manager.remove(manager.contains(doc) ? doc : manager.merge(doc));
			manager.flush();
			manager.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			fechaConexao();
		}
		
		return null;
	}

	public void excluir(Cliente cli,Documento doc) {
		
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(doc) ? doc : manager.merge(doc));
		
	}
	
	@Override
	public Resultado listar(EntidadeDominio ent) {
		abrirConexao();
		Resultado resultado = new Resultado();
		String jpql = "select d from Documento d where d.ativo = true";
		
		try {
			
			manager.getTransaction().begin();
			List<Documento> lista = manager.createQuery(jpql, Documento.class).getResultList();
			manager.flush();
			manager.getTransaction().commit();
			
			for(Documento d : lista) {
				resultado.add(d);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			fechaConexao();
		}
		return resultado;
	}
	
	public Documento busca(int id) {
		String jpql_e = "select c from Documento c where c.id = :id";
		return  manager.createQuery(jpql_e, Documento.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	

	

	
}
