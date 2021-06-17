package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.CartaoPedido;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.Resultado;

@Stateful
public class PedidoDao implements IDao{

	@PersistenceContext	
	private EntityManager manager;
	
	public void salvarCartao(CartaoPedido cp) {
		manager.persist(cp);
	}
	
	public void salvar(Pedido ped) {
		manager.merge(ped.getCliente());
		manager.persist(ped);
	}

	public void editar(Pedido ped) {
		System.out.println("dbg ped:"+ ped.getCupomTroca());
		manager.merge(ped);
	}

	public void excluir(Pedido ped) {
		manager.merge(ped);
		
	}
	
	public List<Pedido> listarTudo() {
		
		String jpql = "select p from Pedido p";
		
		return manager.createQuery(jpql, Pedido.class).getResultList();
	}

	public List<Pedido> listar(int cli) {
		
		String jpql = "select p from Pedido p where p.cliente.id = :id";
		
		return manager.createQuery(jpql, Pedido.class).setParameter("id", cli).getResultList();
	}
	
	public Pedido buscarPorId(int ped) {
		
		String jpql = "select p from Pedido p where p.id = :id";
		
		return manager.createQuery(jpql, Pedido.class).setParameter("id", ped).getSingleResult();
	}
	
	public List<Pedido> grafico() {
		String jpql = "select p from Pedido p order by p.dataAtualizacao desc";
//		String jpql = select c.nome, sum(cp.quantidade) as valorCusto from Carta c 
//		join  CartaPedido cp on c.id = cp.carta_id 
//		join Pedido on Carta
//		group by c.nome 
//		order by valorCusto desc ;
//		
		return manager.createQuery(jpql, Pedido.class).getResultList();
	}

	@Override
	public Resultado salvar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
