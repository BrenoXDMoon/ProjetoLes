package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.STATUS_PEDIDO;

@Model
public class PedidoBean {

	private int id;

	private static Pedido ped = new Pedido();

	private static Pedido trocaPedido = new Pedido();

	private static List<Integer> qtdeTrocada;

	@Inject
	PedidoDao pDao;

	@Inject
	EnderecoDao eDao;

	@Transactional
	public List<Pedido> pedidos(int cli_id) {
		return pDao.listar(cli_id);
	}

	public void nan() {
		Map<Pedido, List<Integer>> cartasTroca = new HashMap<Pedido, List<Integer>>();
		trocaPedido.setId(ped.getId());
		for (int i : qtdeTrocada) {
			System.out.println(i);
		}
		for (CartaPedido crp : ped.getItens()) {
			System.out.println(crp.getCarta().getNome());
//			crp.setQuantidade();
//			trocaPedido.getItens().add(crp);
//			trocaPedido.getItens().forEach(null);
		}

	}

	@Transactional
	public List<Pedido> todosPedidos() {
		return pDao.listarTudo();
	}

	@Transactional
	public String editar() {
		pDao.editar(ped);
		return "/admin/pedido/lista?faces-redirect=true";
	}

	public List<Integer> estoqueMax(Integer max) {
		List<Integer> lista = new ArrayList<Integer>();
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

	public Pedido getTrocaPedido() {
		return trocaPedido;
	}

	public void setTrocaPedido(Pedido trocaPedido) {
		PedidoBean.trocaPedido = trocaPedido;
	}

	public List<Integer> getQtdeTrocada() {
		return qtdeTrocada;
	}

	public void setQtdeTrocada(List<Integer> qtdeTrocada) {
		PedidoBean.qtdeTrocada = qtdeTrocada;
	}

}
