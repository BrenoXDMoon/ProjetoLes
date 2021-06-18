package br.com.newstation.daos;

import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class CartaPedidoDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		abrirConexao();

		Resultado resultado = new Resultado();
		CartaPedido carta = (CartaPedido) ent;

		try {
			manager.getTransaction().begin();
			manager.persist(carta);

			manager.flush();
			manager.getTransaction().commit();
			fechaConexao();

			resultado.setEntidade(carta);

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
		CartaPedido carta = (CartaPedido) ent;

		manager.getTransaction().begin();

		manager.merge(carta);

		manager.getTransaction().commit();
		fechaConexao();

		resultado.setEntidade(carta);

		return resultado;

	}

	@Override
	@Deprecated
	public Resultado excluir(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

}
