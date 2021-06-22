package br.com.newstation.daos;

import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.Resultado;

public class EstoqueDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		Resultado resultado = new Resultado();
		Estoque estoque = (Estoque) ent;
		abrirConexao();
		try {
			manager.getTransaction().begin();
			manager.persist(estoque);
			manager.flush();
			manager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		fechaConexao();
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		Resultado resultado = new Resultado();
		Estoque estoque = (Estoque) ent;
		System.out.println("estoque qtde" + estoque.getQuantidade());
		abrirConexao();
//		try {
			manager.getTransaction().begin();
			manager.merge(estoque);
			manager.flush();
			manager.getTransaction().commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		fechaConexao();
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		Resultado resultado = new Resultado();
		Estoque estoque = (Estoque) ent;
		abrirConexao();
		manager.remove(estoque);
		fechaConexao();
		return resultado;
	}

	public Estoque update(Estoque estoque) {
		abrirConexao();
		manager.getTransaction().begin();
		Estoque estoqueFind = manager.getReference(Estoque.class, estoque.getId());
		estoqueFind.setQuantidade(estoque.getQuantidade());
		manager.merge(estoque);
		manager.getTransaction().commit();
		fechaConexao();
		return estoqueFind;
	}

	@Override
	@Deprecated
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}
}
