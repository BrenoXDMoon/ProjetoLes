package br.com.newstation.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.STATUS_PEDIDO;

@Model
public class PedidoBean {

	
	private int id;
	
	private static Pedido ped =  new Pedido();
	
	@Inject
	PedidoDao pDao;
	
	@Inject 
	EnderecoDao eDao;
	
	@Transactional
	public List<Pedido> pedidos(int cli_id){
		return pDao.listar(cli_id);
	}
	
	@Transactional
	public List<Pedido> todosPedidos(){
		return pDao.listarTudo();
	}
	
	@Transactional
	public String editar(){
		pDao.editar(ped);
		return "/admin/pedido/lista?faces-redirect=true";
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
	
}
