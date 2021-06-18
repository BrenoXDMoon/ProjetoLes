package br.com.newstation.daos;

import java.util.List;

import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteCartao;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

public class CartaoCreditoDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		abrirConexao();

		Resultado resultado = new Resultado();
		ClienteCartao cliAux = (ClienteCartao) ent;
		CartaoCredito card = (CartaoCredito) cliAux.getCard();

		try {
			manager.getTransaction().begin();
			manager.persist(card);
			manager.merge(cliAux.getCliente());

			manager.flush();
			manager.getTransaction().commit();
			fechaConexao();

			resultado.setEntidade(card);

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
		CartaoCredito card = (CartaoCredito) ent;

		manager.getTransaction().begin();

		manager.merge(manager.contains(card) ? card : manager.merge(card));

		manager.getTransaction().commit();
		fechaConexao();

		resultado.setEntidade(card);

		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		ClienteCartao cliAux = (ClienteCartao) ent;
		CartaoCredito card = (CartaoCredito) cliAux.getCard();
		Cliente cli = cliAux.getCliente();

		manager.getTransaction().begin();

		manager.merge(manager.contains(cli) ? cli : manager.merge(cli));
		manager.remove(manager.contains(card) ? card : manager.merge(card));

		manager.getTransaction().commit();
		fechaConexao();

		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		abrirConexao();

		String jpql = "select d from CartaoCredito d where d.ativo = true";
		List<CartaoCredito> lista = manager.createQuery(jpql, CartaoCredito.class).getResultList();
		Resultado resultado = new Resultado();

		for (CartaoCredito c : lista) {
			resultado.add(c);
		}

		fechaConexao();
		return resultado;
	}

	public List<CartaoCredito> listar(Cliente cli) {
		abrirConexao();
		String jpql = "select d from CartaoCredito d where d.ativo = true";
		List<CartaoCredito> cc = manager.createQuery(jpql, CartaoCredito.class).getResultList();
		fechaConexao();
		return cc;
	}

	public CartaoCredito busca(int id) {
		abrirConexao();
		String jpql_e = "select c from CartaoCredito c where c.id = :id";
		CartaoCredito cc = manager.createQuery(jpql_e, CartaoCredito.class).setParameter("id", id).getSingleResult();
		fechaConexao();
		return cc;
	}

}
