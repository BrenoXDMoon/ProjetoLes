package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteAux;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class DocumentoDao extends AbstractDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		
		ClienteAux cliAux = (ClienteAux)ent;
		
		Documento doc = (Documento) cliAux.getEnt();
		manager.persist(doc );
		manager.merge(cliAux.getCliente());
		return null;
	}
	
	@Override
	public Resultado editar(EntidadeDominio ent) {
		
		Documento doc = (Documento) ent;
		
		manager.merge(manager.contains(doc) ? doc : manager.merge(doc));
		
		return null;
	}
	
	@Override
	public Resultado excluir(EntidadeDominio ent) {

		ClienteAux cliAux = (ClienteAux)ent;
		
		Cliente cli = cliAux.getCliente();
		Documento doc = (Documento) cliAux.getEnt();
		
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(doc) ? doc : manager.merge(doc));
		return null;
	}

	public void excluir(Cliente cli,Documento doc) {
		
		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(doc) ? doc : manager.merge(doc));
		
	}
	
	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		Resultado res = new Resultado();
		String jpql = "select d from Documento d where d.ativo = true";
		List<Documento> lista = manager.createQuery(jpql, Documento.class).getResultList();
		
		for(Documento d : lista) {
			res.add(d);
		}
		return res;
	}
	
	public Documento busca(int id) {
		String jpql_e = "select c from Documento c where c.id = :id";
		return  manager.createQuery(jpql_e, Documento.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	

	

	
}
