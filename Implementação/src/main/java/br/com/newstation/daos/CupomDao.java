package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;

import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class CupomDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		abrirConexao();

		Resultado resultado = new Resultado();
		Cupom cupom = (Cupom) ent;

		try {
			manager.getTransaction().begin();
			System.out.println("dbg cupom:" + cupom.getPreco());
			manager.persist(cupom);

			manager.flush();
			manager.getTransaction().commit();
			fechaConexao();

			resultado.setEntidade(cupom);

			return resultado;

		} catch (Exception e) {

			System.out.println("- ERRO AO SALVAR!!!");

			return null;
		}

	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Cupom cupom = (Cupom) ent;

		manager.getTransaction().begin();

		manager.merge(cupom);

		manager.getTransaction().commit();
		fechaConexao();

		resultado.setEntidade(cupom);

		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Cupom cupom = (Cupom) ent;

		manager.getTransaction().begin();

		Cupom cupomDelete = manager.getReference(Cupom.class, cupom.getId());
		cupomDelete.setAtivo(false);

		manager.getTransaction().commit();
		fechaConexao();

		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		abrirConexao();

		Resultado res = new Resultado();

		String jpql = "select C from Cupom C ";

		for (Cupom c : manager.createQuery(jpql, Cupom.class).getResultList()) {
			res.add(c);
		}

		fechaConexao();
		return res;
	}

	public List<Cupom> listarAtivos() {
		abrirConexao();
		String jpql = "select C from Cupom C where C.ativo = 1";
		List<Cupom> cupom = manager.createQuery(jpql, Cupom.class).getResultList();
		fechaConexao();
		return cupom;
	}

	public List<Cupom> listarCuponsDesconto() {
		abrirConexao();
		String jpql = "select C from Cupom C where C.tipoCupom = 'Desconto' and C.ativo = 1";
		List<Cupom> cupom = manager.createQuery(jpql, Cupom.class).getResultList();
		fechaConexao();
		return cupom;
	}

	public List<Cupom> listarCuponsTroca() {
		abrirConexao();
		String jpql = "select C from Cupom C where C.tipoCupom = 'Troca' and C.ativo = 1";
		List<Cupom> cupom = manager.createQuery(jpql, Cupom.class).getResultList();
		fechaConexao();
		return cupom;
	}

	public List<Cupom> listarCuponsByCliente(Integer cliente) {
		String jpql = "select c from Cupom c where c.cliente.id = :cliente and c.ativo = true";
		List<Cupom> cupom = manager.createQuery(jpql, Cupom.class).setParameter("cliente", cliente).getResultList();
		fechaConexao();
		return cupom;
	}

	public Cupom buscarById(Integer id) {
		abrirConexao();
		String jpql = "select distinct(c) from Cupom c where c.id = :id";
		Cupom cupom = manager.createQuery(jpql, Cupom.class).setParameter("id", id).getSingleResult();
		fechaConexao();
		return cupom;
	}
}
