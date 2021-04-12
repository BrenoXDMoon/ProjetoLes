package br.com.newstation.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaPedidoDao;
import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.STATUS_PEDIDO;
import br.com.newstation.strategies.GeraCupomTroca;

@Model
public class PedidoBean {

	@Inject
	private EstoqueDao daoE;
	
	@Inject
	CartaPedidoDao cpedDao;
	
	private int id;

	private static Pedido ped = new Pedido();
	
	private static Pedido repasse = new Pedido();
	
	LoginBean lb = new LoginBean();
	
	public static Pedido getRepasse() {
		return repasse;
	}

	public static void setRepasse(Pedido repasse) {
		PedidoBean.repasse = repasse;
	}

	private static List<CartaPedido> carped = new ArrayList<CartaPedido>();

	@Inject
	PedidoDao pDao;

	@Inject
	EnderecoDao eDao;

	public PedidoBean() {
		carped = new ArrayList<>(ped.getItens());
	}

	public boolean isTroca() {
		return ped.getStatusPedido() == STATUS_PEDIDO.Em_Troca;
	}

	public void aSerTrocado() {
		repasse = ped;
		repasse.setItens(new HashSet<CartaPedido>(carped));
	}

	@Transactional
	public List<Pedido> pedidos(int cli_id) {
		return pDao.listar(cli_id);
	}

	public String nan() {
		for (CartaPedido c : carped) {
			if (c.getQuantidade() == null) {
				c.setQuantidade(0);
			}
			System.out.println("ce:" + c.getCarta().getNome());
			System.out.println("ce:" + c.getQuantidade());
		}
		ped.setStatusPedido(STATUS_PEDIDO.Em_Troca);
		pDao.editar(ped);
		
		return "/cliente/perfil?faces-redirect=trueid="+lb.getId();
	}

	@Transactional
	public List<Pedido> todosPedidos() {
		return pDao.listarTudo();
	}

	public int getIndex(CartaPedido item) {
		return new ArrayList<>(ped.getItens()).indexOf(item);
	}

	@Transactional
	public String editar() {
		pDao.editar(ped);
		return "/admin/pedido/lista?faces-redirect=true";
	}
	
	@Transactional
	public String editarTrocaAceita() {
		ped.setStatusPedido(STATUS_PEDIDO.Trocado);
		BigDecimal totalTrocados = new BigDecimal(0);
		for (CartaPedido crp : carped) {
			devolveEstoque(crp.getCarta(),crp.getQuantidade());
			cpedDao.editar(crp);
			
			totalTrocados.add(crp.getCarta().getPreco());
		}
		
		pDao.editar(ped);
		
		GeraCupomTroca.gerarCupom(ped.getTotal());		
		return "/admin/pedido/lista?faces-redirect=true";
	}
	
	@Transactional
	public void devolveEstoque(Carta carta, int quantidade) {
		carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() + quantidade);
		System.out.println("CARTAOZAO ESOSQUESKE"+ carta.getEstoque().getQuantidade()+"id"+carta.getId());
		daoE.editar(carta.getEstoque());
	}
	
	@Transactional
	public String editarTrocaNegada() {
		ped.setStatusPedido(STATUS_PEDIDO.Troca_negada);
		pDao.editar(ped);
		return "/admin/pedido/lista?faces-redirect=true";
	}

	public List<Integer> estoqueMax(Integer max) {
		List<Integer> lista = new ArrayList<Integer>();
		if (max == null) {
			max = 0;
		}

		for (int i = 1; i <= max; i++) {
			lista.add(i);
		}
		return lista;
	}

	public boolean entregue() {
		if (ped.getStatusPedido().equals(STATUS_PEDIDO.Entregue)) {
			return true;
		} else {
			return false;
		}
	}

	public STATUS_PEDIDO[] getStatusPedido() {
		return STATUS_PEDIDO.values();
	}

	public Endereco getEndereco() {
		return ped.getEndereco();
	}

	public void carregar() {
		PedidoBean.ped = pDao.buscarPorId(this.id);
	}

	public Pedido getPed() {
		return ped;
	}

	public void setPed(Pedido ped) {
		PedidoBean.ped = ped;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CartaPedido> getCarped() {
		return carped;
	}

	public void setCarped(List<CartaPedido> carped) {
		this.carped = carped;
	}

}
