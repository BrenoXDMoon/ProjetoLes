package br.com.newstation.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.command.ListarCommand;
import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.CartaPedidoDao;
import br.com.newstation.daos.CupomDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.STATUS_PEDIDO;
import br.com.newstation.strategies.GeraCupomTroca;

@Model
public class PedidoBean {

	@Inject
	EstoqueDao daoE;

	@Inject
	CartaPedidoDao cpedDao;

	@Inject
	CupomDao cDao;

	@Inject
	PedidoDao pDao;

	private int id;

	private boolean troca = false;

	private static Pedido ped = new Pedido();

	private static List<CartaPedido> repasse = new ArrayList<CartaPedido>();

	private static List<CartaPedido> carped = new ArrayList<CartaPedido>();

	private static int totalPedidos = 0;

	private String busca = "";

	EditarCommand cmdEditar = new EditarCommand();
	
	SalvarCommand cmdSalvar = new SalvarCommand();
	
	LoginBean lb = new LoginBean();

	public void paginacaoAdmin() {
		totalPedidos = todosPedidos().size();
	}

	public List<Pedido> filtraPedido() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos = pDao.filtro(getBusca());
		return pedidos;
	}

	public void setMaxPedido(int cli_id) {
		totalPedidos = pDao.listarByCliente(cli_id).size();
	}

	public void carpedido() {
		carped = new ArrayList<CartaPedido>(ped.getItens());
	}

	@Transactional
	public List<Pedido> pedidos(int cli_id) {
		return pDao.listarByCliente(cli_id);
	}

	public String trocaPedido() {
		for (CartaPedido c : carped) {
			if (c.getQuantidade() == null) {
				c.setQuantidade(0);

			}
		}
		ped.setStatusPedido(STATUS_PEDIDO.Em_Troca);
		
		cmdEditar.executar(ped);

		return "/cliente/perfil?faces-redirect=trueid=" + lb.getId();
	}

	public void status(Pedido p) {
		troca = false;
		if (p.getStatusPedido() == STATUS_PEDIDO.Em_Troca) {
			troca = true;
		}
	}

	@Transactional
	public List<Pedido> todosPedidos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		if (getBusca().equals("")) {
			ListarCommand cmd = new ListarCommand();
			for (EntidadeDominio e : cmd.executar(new Pedido()).getEntidades()) {
				Pedido ped = (Pedido) e;
				lista.add(ped);
			}
		} else {
			return pDao.filtro(getBusca());
		}
		return lista;
	}

	public int getIndex(CartaPedido item) {
		return new ArrayList<>(ped.getItens()).indexOf(item);
	}

	@Transactional
	public String editar() {
		EditarCommand cmd = new EditarCommand();
		cmd.executar(ped);
		return "/admin/pedido/lista?faces-redirect=true";
	}

	@Transactional
	public String editarTrocaAceita() {
//		ped.setStatusPedido(STATUS_PEDIDO.Trocado);

		Double totalTrocados = 0.;
		for (CartaPedido crp : carped) {

			totalTrocados += crp.getCarta().getPreco().doubleValue() * crp.getQuantidade();

			for (CartaPedido cartaEstoque : ped.getItens()) {

				if (crp.getCarta().getId() == cartaEstoque.getCarta().getId()) {

					crp.setQuantidade(Math.abs(crp.getQuantidade() - cartaEstoque.getQuantidade()));
					cmdEditar.executar(crp);
				}

				devolveEstoque(crp.getCarta(), crp.getQuantidade());
			}

		}
		ped.setStatusPedido(STATUS_PEDIDO.Trocado);
		cmdEditar.executar(ped);

		BigDecimal valorCupom = new BigDecimal(totalTrocados).setScale(2, RoundingMode.DOWN);
		cmdSalvar.executar(GeraCupomTroca.gerarCupom(valorCupom, ped.getCliente()));
		return "/admin/pedido/lista?faces-redirect=true";
	}

	@Transactional
	public void devolveEstoque(Carta carta, int quantidade) {
		carta.getEstoque().setQuantidade(carta.getEstoque().getQuantidade() + quantidade);
			daoE.editar(carta.getEstoque());
	}

	@Transactional
	public String editarTrocaNegada() {
		ped.setStatusPedido(STATUS_PEDIDO.Troca_negada);
		EditarCommand cmd = new EditarCommand();
		cmd.executar(ped);
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

	public void setTroca(boolean troca) {
		this.troca = troca;
	}

	public boolean isTroca() {
		return troca;
	}

	public int getTotalPedidos() {
		return totalPedidos;
	}

	public void setTotalPedidos(int totalPedidos) {
		PedidoBean.totalPedidos = totalPedidos;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
