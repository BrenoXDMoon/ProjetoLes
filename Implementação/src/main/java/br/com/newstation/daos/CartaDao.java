package br.com.newstation.daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;

@Stateful
public class CartaDao extends AbstractDao {

	@Override
	public Resultado salvar(EntidadeDominio ent) {

		abrirConexao();

		Resultado resultado = new Resultado();
		Carta carta = (Carta) ent;

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
		Carta carta = (Carta) ent;

		manager.getTransaction().begin();

		System.out.println("valor estoque pre merge "+carta.getEstoque().getQuantidade());
		manager.merge(carta);

		manager.getTransaction().commit();
		fechaConexao();
		
		resultado.setEntidade(carta);

		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		abrirConexao();

		Resultado resultado = new Resultado();
		Carta carta = (Carta) ent;

		String jpql = "select distinct(c) from Carta c where c.id=:id";

		manager.getTransaction().begin();

		Carta cartaFind = manager.createQuery(jpql, Carta.class).setParameter("id", carta.getId()).getSingleResult();

		cartaFind.setAtivo(false);

		manager.getTransaction().commit();
		fechaConexao();
		
		resultado.setEntidade(carta);

		return resultado;

	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		abrirConexao();

		String jpql = "select distinct(c) from Carta c";

		Resultado resultado = new Resultado();

		List<Carta> lista = new ArrayList<Carta>();

		manager.getTransaction().begin();
		lista = manager.createQuery(jpql, Carta.class).getResultList();

		manager.getTransaction().commit();
		this.manager.close();
		this.factory.close();

		for (Carta c : lista) {
			resultado.add(c);
		}

		return resultado;
	}

	public List<Carta> listarAll() {
		abrirConexao();
		String jpql = "select C from Carta C ";
		List<Carta> cartas = manager.createQuery(jpql, Carta.class).getResultList();
		fechaConexao();
		return cartas;
	}

	public Carta buscarPorId(Integer id) {
		abrirConexao();
		String jpql = "select c from Carta c where c.id = :id";
		Carta carta = manager.createQuery(jpql, Carta.class).setParameter("id", id).getSingleResult();
		fechaConexao();
		return carta;
	}

	public List<Carta> filtro(String busca) {
		abrirConexao();
		try {
			List<Carta> cartas = manager.createQuery("select c from Carta c join fetch c.estoque where c.nome LIKE '%" + busca
					+ "%' and c.ativo = 1 and c.estoque.quantidade > 0", Carta.class).getResultList();
			fechaConexao();
			return cartas;
		} catch (Exception e) {
			return null;
		}
	}

}
