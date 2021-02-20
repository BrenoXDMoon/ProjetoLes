package br.com.newstation.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartaoCredito {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String numero;
	private String codigoSeguranca;
	private String nomeImpresso;
	private String validade;

	@Enumerated(EnumType.STRING)
	private BANDEIRA bandeira;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public String getNomeImpresso() {
		return nomeImpresso;
	}

	public void setNomeImpresso(String nomeImpresso) {

		this.nomeImpresso = nomeImpresso;
		this.validade = validade;
		this.codigoSeguranca = codigoSeguranca;
	}

	public CartaoCredito() {

	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public BANDEIRA getBandeira() {
		return bandeira;
	}


	public void setBandeira(BANDEIRA bandeira) {
		this.bandeira = bandeira;
	}

}
