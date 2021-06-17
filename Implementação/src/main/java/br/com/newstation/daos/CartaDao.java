package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
@Stateful
public class CartaDao extends AbstractDao{ 

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		Carta carta = (Carta) ent;
		Resultado res = new Resultado();
		manager.persist(carta);
		return res;
	}
	
	@Override
	public Resultado editar(EntidadeDominio ent) {
		Resultado res = new Resultado();
		Carta carta = (Carta) ent;
		manager.merge(carta);
		return res;
	}
	
	@Override
	public Resultado excluir(EntidadeDominio ent) {
		Resultado res = new Resultado();
		Carta carta = (Carta) ent;
		Carta cartaDelete = manager.getReference(Carta.class, carta.getId());
		cartaDelete.setAtivo(false);
		return res;
	}
	
	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		String jpql = "select C from Carta C join fetch C.estoque where C.ativo=true and C.estoque.quantidade > 0";
		Resultado res = new Resultado();
		
		for(Carta c : manager.createQuery(jpql, Carta.class).getResultList()) {
			res.add(c);
		}
		
		return res;
	}
	
	public List<Carta> listarAll() {
		
		String jpql = "select C from Carta C ";
				
		return manager.createQuery(jpql, Carta.class).getResultList();
	}

	public Carta buscarPorId(Integer id) {
		String jpql = "select c from Carta c where c.id = :id";
		return manager.createQuery(jpql, Carta.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Carta> filtro(String busca) {
		
		try {
			return manager.createQuery("select c from Carta c join fetch c.estoque where c.nome LIKE '%" + busca + "%' and c.ativo = 1 and c.estoque.quantidade > 0", 
					Carta.class)
					.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
}
