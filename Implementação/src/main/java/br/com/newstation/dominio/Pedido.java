package br.com.newstation.dominio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	private String uuid;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Cliente cliente;
	
	@ManyToOne
	private Endereco endereco;
	
	@OneToMany
	private Set<CartaoCredito> cartoes= new HashSet<CartaoCredito>();
	
	@OneToMany
	private Set<Carta> itens= new HashSet<Carta>();
	
	@Temporal(TemporalType.DATE)
	protected Calendar dataAtualizacao; 
	private BigDecimal total;
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Set<Carta> getItens() {
		return itens;
	}

	public void setItens(Set<Carta> itens) {
		this.itens = itens;
	}

}
