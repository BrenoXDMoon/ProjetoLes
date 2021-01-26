package br.com.terrenobenzido.modelo.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartaoCredito extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	

	@ManyToOne
	private Cliente cliente;

	private String numeroCartao;
	private String bandeira;
	private String nomeImpresso;
	private String validade;
	private String codigoSeguranca;

	public CartaoCredito(String numeroCartao, String bandeira, String nomeImpresso, String validade,
			String codigoSeguranca, Cliente cliente) {
		this.numeroCartao = numeroCartao;
		this.bandeira = bandeira;
		this.nomeImpresso = nomeImpresso;
		this.validade = validade;
		this.codigoSeguranca = codigoSeguranca;
		this.cliente = cliente;
	}

	// Constroi cartao sem o cliente
	public CartaoCredito(String numeroCartao, String bandeira, String nomeImpresso, String validade,
			String codigoSeguranca) {
		this.numeroCartao = numeroCartao;
		this.bandeira = bandeira;
		this.nomeImpresso = nomeImpresso;
		this.validade = validade;
		this.codigoSeguranca = codigoSeguranca;
	}

	public CartaoCredito() {

	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeImpresso() {
		return nomeImpresso;
	}

	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
}
