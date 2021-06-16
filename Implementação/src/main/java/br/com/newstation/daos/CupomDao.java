package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class CupomDao implements IDao{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		Cupom cupom = (Cupom)ent;
		System.out.println("dbg cupom:"+ cupom.getPreco());
		manager.persist(cupom);
		return null;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		Cupom cupom = (Cupom)ent;
		manager.merge(cupom);
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {

		Cupom cupom = (Cupom)ent;
		Cupom cupomDelete = manager.getReference(Cupom.class, cupom.getId());
		cupomDelete.setAtivo(false);
		return null;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		Resultado res = new Resultado();
		
		String jpql = "select C from Cupom C ";
		
		for(Cupom c :  manager.createQuery(jpql, Cupom.class).getResultList()) {
			res.add(c);
		}
		return res;
	}
	
	public List<Cupom> listarAtivos(){
		String jpql = "select C from Cupom C where C.ativo = 1";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
	public List<Cupom> listarCuponsDesconto(){
		String jpql = "select C from Cupom C where C.tipoCupom = 'Desconto' and C.ativo = 1";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
	public List<Cupom> listarCuponsTroca(){
		String jpql = "select C from Cupom C where C.tipoCupom = 'Troca' and C.ativo = 1";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
	public List<Cupom> listarCuponsByCliente(Integer cliente){
		String jpql = "select c from Cupom c where c.cliente.id = :cliente and c.ativo = true";
		
		return manager.createQuery(jpql, Cupom.class).
				setParameter("cliente", cliente).
				getResultList();
	}

	public Cupom buscarById(Integer id) {
		
		String jpql = "select distinct(c) from Cupom c where c.id = :id";
		
		return manager.createQuery(jpql, Cupom.class).setParameter("id", id).getSingleResult();
	}
}
