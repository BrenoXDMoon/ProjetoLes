package br.com.newstation.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.CartaoPedido;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.Resultado;

@Stateful
public class PedidoDao extends AbstractDao{
	
	@Override
	public Resultado salvar(EntidadeDominio ent) {
		abrirConexao();
		Resultado resultado = new Resultado();
		Pedido ped = (Pedido) ent;
	try {
		manager.getTransaction().begin();
		manager.merge(ped.getCliente());
		manager.persist(ped);
		manager.flush();
		manager.getTransaction().commit();
		
	}catch(NullPointerException e) {
		
		System.out.println("- ERRO AO SALVAR!!!");
		resultado.setMensagem("- ERRO AO SALVAR!!!");
		
	}finally {
		fechaConexao();
	}
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio ent) {

		abrirConexao();
		Resultado resultado = new Resultado();
		Pedido ped = (Pedido) ent;
		manager.getTransaction().begin();
		manager.merge(ped);
		manager.getTransaction().commit();
		
		fechaConexao();
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio ent) {
		abrirConexao();
		Resultado resultado = new Resultado();
		Pedido ped = (Pedido) ent;
		manager.getTransaction().begin();
		manager.merge(ped);
		manager.getTransaction().commit();
		
		fechaConexao();
		
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio ent) {
		
		abrirConexao();
		Resultado resultado = new Resultado();
		String jpql = "select p from Pedido p";
		
		manager.getTransaction().begin();
		List<Pedido> lista = manager.createQuery(jpql, Pedido.class).getResultList();
		manager.getTransaction().commit();
		
		fechaConexao();
		for(Pedido p : lista) {
			resultado.add(p);
		}
	
		return resultado;
	}
	
	public void salvarCartao(CartaoPedido cp) {
		
		abrirConexao();
		
		manager.getTransaction().begin();
		manager.persist(cp);
		manager.getTransaction().commit();
		fechaConexao();
	}

	public List<Pedido> listarByCliente(int cli) {
		abrirConexao();
		String jpql = "select p from Pedido p where p.cliente.id = :id";
		manager.getTransaction().begin();
		List<Pedido> lista = manager.createQuery(jpql, Pedido.class).setParameter("id", cli).getResultList();
		manager.getTransaction().commit();
		fechaConexao();
		return lista;
	}
	
	public Pedido buscarPorId(int ped) {
		abrirConexao();
		String jpql = "select p from Pedido p where p.id = :id";
		
		manager.getTransaction().begin();
		Pedido pedido = manager.createQuery(jpql, Pedido.class).setParameter("id", ped).getSingleResult();
		manager.getTransaction().commit();
		
		fechaConexao();
		return pedido; 
	}
	
	public List<Pedido> grafico() {
		
		abrirConexao();
		String jpql = "select p from Pedido p order by p.dataAtualizacao asc";
		
		manager.getTransaction().begin();
		List<Pedido> lista = manager.createQuery(jpql, Pedido.class).getResultList();
		manager.getTransaction().commit();
		
		fechaConexao();
		return lista ;
	}
	
	
	public List<Pedido> filtro(String busca) {
		abrirConexao();
		try {
			List<Pedido> pedidos = manager.createQuery("select c from Pedido c where c.statusPedido LIKE '%" + busca
					+ "%' ", Pedido.class).getResultList();
			fechaConexao();
			return pedidos;
		} catch (Exception e) {
			return null;
		}
	}
}
