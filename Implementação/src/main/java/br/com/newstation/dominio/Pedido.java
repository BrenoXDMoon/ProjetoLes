package br.com.newstation.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido extends EntidadeDominio{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	private String uuid;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Endereco endereco = new Endereco();

	@OneToMany(fetch = FetchType.EAGER)
	private Set<CartaoPedido> cartoes = new HashSet<CartaoPedido>();

	@OneToMany(fetch = FetchType.EAGER)
	private Set<CartaPedido> itens= new HashSet<CartaPedido>();

	@Temporal(TemporalType.DATE)
	protected Calendar dataAtualizacao;
	

	@OneToOne(fetch = FetchType.EAGER)
	private Cupom cupomDesconto = new Cupom();

	@OneToMany(fetch = FetchType.EAGER)
	private List<Cupom> cupomTroca = new ArrayList<Cupom>();

	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	private STATUS_PEDIDO statusPedido;

	@PrePersist
	public void createUUID() {
		this.uuid = UUID.randomUUID().toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<CartaoPedido> getCartoes() {
		return cartoes;
	}

	public void setCartoes(Set<CartaoPedido> cartoes) {
		this.cartoes = cartoes;
	}

	public Set<CartaPedido> getItens() {
		return itens;
	}

	public void setItens(Set<CartaPedido> itens) {
		this.itens = itens;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Cupom getCupomDesconto() {
		return cupomDesconto;
	}

	public void setCupomDesconto(Cupom cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
	}

	public List<Cupom> getCupomTroca() {
		return cupomTroca;
	}

	public void setCupomTroca(List<Cupom> cupomTroca) {
		this.cupomTroca = cupomTroca;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public STATUS_PEDIDO getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(STATUS_PEDIDO statusPedido) {
		this.statusPedido = statusPedido;
	}

}
