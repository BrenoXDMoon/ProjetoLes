package br.com.newstation.beans;

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

@Model
public class PedidoBean {

	@Inject
	private EstoqueDao daoE;

	@Inject
	CartaPedidoDao cpedDao;

	private int id;

	private static Pedido ped = new Pedido();

	private static List<CartaPedido> repasse = new ArrayList<CartaPedido>();

	private static List<CartaPedido> carped = new ArrayList<CartaPedido>();

	LoginBean lb = new LoginBean();

	@Inject
	PedidoDao pDao;

	@Inject
	EnderecoDao eDao;

	public void carpedido() {
		carped = new ArrayList<CartaPedido>(ped.getItens());
	}

	public boolean isTroca() {
		return ped.getStatusPedido() == STATUS_PEDIDO.Em_Troca;
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
		}
		ped.setStatusPedido(STATUS_PEDIDO.Em_Troca);
		pDao.editar(ped);

		return "/cliente/perfil?faces-redirect=trueid=" + lb.getId();
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
		for (CartaPedido crp : carped) {
			for (CartaPedido cartaEstoque : ped.getItens()) {

				if (crp.getCarta().getId() == cartaEstoque.getCarta().getId()) {

					crp.setQuantidade(Math.abs(crp.getQuantidade() - cartaEstoque.getQuantidade()));
					cpedDao.editar(crp);
				}

				devolveEstoque(crp.getCarta(), crp.getQuantidade());
			}
		}

		pDao.editar(ped);
		return "/admin/pedido/lista?faces-redirect=true";
	}

	@Transactional
	public void devolveEstoque(Carta carta, int quantidade) {
		carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() + quantidade);
//		System.out.println("CARTAOZAO ESOSQUESKE: "+ "nome"+carta.getNome()+" qtde: "+carta.getEstoque().getQuantidade());
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

	public static List<CartaPedido> getRepasse() {
		return repasse;
	}

	public static void setRepasse(List<CartaPedido> repasse) {
		PedidoBean.repasse = repasse;
	}

}
