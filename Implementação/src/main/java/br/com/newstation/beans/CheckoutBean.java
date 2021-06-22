package br.com.newstation.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.ExcluirCommand;
import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.CartaPedidoDao;
import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.CupomDao;
import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.Carrinho;
import br.com.newstation.dominio.CarrinhoItem;
import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.CartaoPedido;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.dominio.STATUS_PEDIDO;
import br.com.newstation.strategies.GeraCupomTroca;
import br.com.newstation.strategies.ValidaCupomDescontoPedido;
import br.com.newstation.strategies.ValidaValoresPagamento;

@Model
public class CheckoutBean {

	@Inject
	ClienteDao dao;

	@Inject
	CupomDao cDao;

	@Inject
	EnderecoDao eDao;

	@Inject
	CartaoCreditoDao ccDao;

	@Inject
	PedidoDao pDao;

	@Inject
	CartaPedidoDao cpedDao;
	BigDecimal valorCartaoUm;
	BigDecimal valorCartaoDois;
	private Cupom cupom = new Cupom();
	private List<Cupom> cupons = new ArrayList<Cupom>();
	private Endereco end = new Endereco();
	private Integer idCli;
	private Set<Endereco> enderecos = new HashSet<Endereco>();
	private Set<CartaoCredito> cartoes = new HashSet<CartaoCredito>();
	private Cliente cliente = new Cliente();
	private CartaoCredito cd = new CartaoCredito();
	private Pedido pedido = new Pedido();
	private double somaCupom = 0.0;
	ValidaValoresPagamento validapagamento = new ValidaValoresPagamento();
	ValidaCupomDescontoPedido validaDesconto = new ValidaCupomDescontoPedido();
	SalvarCommand cmd = new SalvarCommand();
	ExcluirCommand cmdE = new ExcluirCommand();
	
	@Transactional
	public String salvar(Integer id, BigDecimal total, Carrinho carrinho) {

		
		Cliente cli = new Cliente();
		Calendar cale = Calendar.getInstance();
		Set<CartaoPedido> cardPed = new HashSet<CartaoPedido>();
		CartaoPedido car = new CartaoPedido();
		BigDecimal valor = new BigDecimal(Math.abs(total.doubleValue() - somaCupom));

		cli.setId(id);
		cale.setTime(cale.getTime());

		pedido.setDataAtualizacao(cale);
		pedido.setCliente(dao.visualizar(cli));

		if(validaDesconto.processar(cupom) == "") {
			cupom = null;
			pedido.setCupomDesconto(cupom);
		} else {
			if (validapagamento.total(cupons, total, cDao.buscarById(cupom.getId())))
				return "/checkout/checkout?faces-redirect=true";
			pedido.setCupomDesconto(cDao.buscarById(cupom.getId()));
		}

		if (!cupons.isEmpty()) {
			pedido.setCupomTroca(cupons);
		} else {
			pedido.setCupomTroca(null);
		}

		if (somaCupom > total.doubleValue()) {
			Resultado resultado = cmd.executar(GeraCupomTroca.gerarCupom(valor, pedido.getCliente()));
		}
		pedido.setEndereco(eDao.busca(end.getId()));

		car.setCartao(cd);
		car.setValor(valor);

		pDao.salvarCartao(car);
		cardPed.add(car);

		pedido.setCartoes(cardPed);
		pedido.setTotal(total);
		for (CarrinhoItem c : carrinho.getItens()) {
			CartaPedido crp = new CartaPedido();
			crp.setCarta(c.getCarta());
			crp.setQuantidade(c.getQuantidade());
			cmd.executar(crp);
//			cpedDao.salvar(crp);
			pedido.getItens().add(crp);

		}

		pedido.setStatusPedido(STATUS_PEDIDO.Pendente);

		cmd.executar(pedido);
//		pDao.salvar(pedido);
		carrinho.resete();

		if (!cupons.isEmpty()) {
			for (Cupom cupom : pedido.getCupomTroca()) {

				cmdE.executar(cupom);
//				cDao.excluir(cupom);
			}
		}
		return "/checkout/confirmaPedido?faces-redirect=true";
	}

	@Transactional
	public String salvarDoisCartoes(Integer id, BigDecimal total, Carrinho carrinho) {

		Cliente cli = new Cliente();
		Calendar cale = Calendar.getInstance();

		cli.setId(id);
		cale.setTime(cale.getTime());

		pedido.setDataAtualizacao(cale);
		pedido.setCliente(dao.visualizar(cli));
		
		if(validaDesconto.processar(cupom) == "") {
			cupom = null;
			pedido.setCupomDesconto(cupom);
		} else {
			if (validapagamento.total(cupons, total, cDao.buscarById(cupom.getId())))
				return "/checkout/checkout?faces-redirect=true";
			pedido.setCupomDesconto(cDao.buscarById(cupom.getId()));
		}

		if (!cupons.isEmpty()) {
			pedido.setCupomTroca(cupons);
		} else {
			pedido.setCupomTroca(null);
		}

		pedido.setEndereco(eDao.busca(end.getId()));

		Set<CartaoPedido> cardPed = new HashSet<CartaoPedido>();

		int a = 0;
		for (CartaoCredito c : cartoes) {
			CartaoPedido car = new CartaoPedido();
			car.setCartao(c);
			if (a == 0) {
				car.setValor(getValorCartaoUm());

			}
			if (a == 1) {
				car.setValor(getValorCartaoDois());
			}
			cardPed.add(car);
			pDao.salvarCartao(car);
			a++;
		}

		pedido.setCartoes(cardPed);
		pedido.setTotal(total);
		for (CarrinhoItem c : carrinho.getItens()) {
			CartaPedido crp = new CartaPedido();
			crp.setCarta(c.getCarta());
			crp.setQuantidade(c.getQuantidade());
			cmd.executar(crp);
//			cpedDao.salvar(crp);
			pedido.getItens().add(crp);

		}

		pedido.setStatusPedido(STATUS_PEDIDO.Pendente);
		cmd.executar(pedido);
//		pDao.salvar(pedido);

		carrinho.resete();
		if (!cupons.isEmpty()) {
			for (Cupom cupom : pedido.getCupomTroca()) {
				cmdE.executar(cupom);
//				cDao.excluir(cupom);
			}
		}

		return "/checkout/confirmaPedido?faces-redirect=true";
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getIdCli() {
		return idCli;
	}

	public void setIdCli(Integer idCli) {
		this.idCli = idCli;
	}

	@Transactional
	public void setIdCliente(Integer i) {
		setIdCli(i);
	}

	public BigDecimal getValorCartaoUm() {
		return valorCartaoUm;
	}

	public void setValorCartaoUm(BigDecimal valorCarTaoUm) {
		this.valorCartaoUm = valorCarTaoUm;
	}

	public BigDecimal getValorCartaoDois() {
		return valorCartaoDois;
	}

	public void setValorCartaoDois(BigDecimal valorCarTaoDois) {
		this.valorCartaoDois = valorCarTaoDois;
	}

	public Set<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setCartoes(Set<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public CartaoCredito getCd() {
		return cd;
	}

	public void setCd(CartaoCredito cd) {
		this.cd = cd;
	}

	public List<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}

	public double getSomaCupom() {
		return somaCupom;
	}

	public void setSomaCupom(double somaCupom) {
		this.somaCupom = somaCupom;
	}

}
