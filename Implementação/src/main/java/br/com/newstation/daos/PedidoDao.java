package br.com.newstation.daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.CartaoPedido;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.Resultado;

@Stateful
public class PedidoDao implements IDao {

	@PersistenceContext	
	private EntityManager manager;
	
	public void salvarCartao(CartaoPedido cp) {
		manager.persist(cp);
	}
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		Pedido ped = (Pedido) ent;
		Resultado res = new Resultado();
		manager.merge(ped.getCliente());
		manager.persist(ped);
		return res;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {
		Resultado res = new Resultado();
		Pedido ped = (Pedido) ent;
		manager.merge(ped);
		return res;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		Resultado res = new Resultado();
		Pedido ped = (Pedido) ent;
		manager.remove(ped);
		return res;
	}
	
	@Override
	public Resultado listar(EntidadeDominio ent) {
		Cliente cliente = (Cliente) ent;
		int id = cliente.getId();
		String jpql = "select p from Pedido p where p.cliente.id = :id"; 
		
		Resultado resultado = new Resultado();

		List<Pedido> lista = new ArrayList<Pedido>();
		
		lista = manager.createQuery(jpql, Pedido.class).setParameter("id", id).getResultList();
		
		for (Pedido p : lista) {
			resultado.add(p);
		}
		
		return resultado;
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

	
	
}
