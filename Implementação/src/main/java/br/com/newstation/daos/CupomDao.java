package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Cupom;

@Stateful
public class CupomDao {

	@PersistenceContext
	EntityManager manager;
	
	public void salvar(Cupom cupom) {
		manager.persist(cupom);
	}
	
	public void editar(Cupom cupom) {
		manager.merge(cupom);
	}
	
	public void excluir(Cupom cupom) {
		Cupom cupomDelete = manager.getReference(Cupom.class, cupom.getId());
		cupomDelete.setAtivo(false);
	}
	
	public List<Cupom> listar(){
		String jpql = "select C from Cupom C ";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
	public List<Cupom> listarCuponsDesconto(){
		String jpql = "select C from Cupom C where C.tipoCupom = 'Desconto'";
		
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}
	
	public List<Cupom> listarCuponsTroca(){
		String jpql = "select C from Cupom C where C.tipoCupom = 'Troca'";
		
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
