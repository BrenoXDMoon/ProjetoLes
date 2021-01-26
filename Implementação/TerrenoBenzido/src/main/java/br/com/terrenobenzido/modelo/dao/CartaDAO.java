package br.com.terrenobenzido.modelo.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.terrenobenzido.modelo.dominio.Carta;

@Stateful
public class CartaDAO {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Carta carta) {
		manager.persist(carta);
	}

	public void delete(Carta carta) {
		Carta cartaDelete = manager.getReference(Carta.class, carta.getId());
		cartaDelete.setAtivo(false);
		System.out.println(buscarPorId(carta.getId()));
	}

	public void editar(Carta carta) {

		manager.merge(carta);
	}

	public void update(Carta carta) {
		Carta cartaFind = manager.getReference(Carta.class, carta.getId());
		cartaFind.setNome(carta.getNome());
		cartaFind.setDescricao(carta.getDescricao());
		cartaFind.setPreco(carta.getPreco());
		cartaFind.setRaridade(carta.getRaridade());
		cartaFind.setEstoque(carta.getEstoque());

		if (carta.getImagemPath() != null) {
			cartaFind.setImagemPath(carta.getImagemPath());
		}

//		manager.remove(manager.contains(carta) ? carta : manager.merge(carta));distinct(C)

	}

	public List<Carta> listar() {

		String jpql = "select C from Carta C where C.ativo=true";

		return manager.createQuery(jpql, Carta.class).getResultList();
	}

	public List<Carta> demaisCartas() {
		String jpql = "select c from Carta c order by c.id desc";
		return manager.createQuery(jpql, Carta.class).setFirstResult(5).getResultList();
	}

	public List<Carta> ultimosLancamentos() {
		String jpql = "select c from Carta c order by c.id desc";
		return manager.createQuery(jpql, Carta.class).setMaxResults(5).getResultList();

	}

	public Carta buscarPorId(Integer id) {
		String jpql = "select c from Carta c where c.id = :id ";
		return manager.createQuery(jpql, Carta.class).setParameter("id", id).getSingleResult();
	}

	public Carta buscarPorId(Long id) {
		String jpql = "select c from Carta c "

				+ "where l.id = :id";
		return manager.createQuery(jpql, Carta.class).setParameter("id", id).getSingleResult();
	}

}
