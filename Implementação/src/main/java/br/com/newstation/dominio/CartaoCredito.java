package br.com.newstation.dominio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CartaoCredito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String numeroCartao;
	
	@Enumerated(EnumType.STRING)
	private BANDEIRA bandeira;
	private String nomeImpresso;
	private String validade;
	private String codigoSeguranca;

	public CartaoCredito(String numeroCartao, BANDEIRA bandeira, String nomeImpresso, String validade,
			String codigoSeguranca, Cliente cliente) {
		this.numeroCartao = numeroCartao;
		this.setBandeira(bandeira);
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

	public BANDEIRA getBandeira() {
		return bandeira;
	}

	public void setBandeira(BANDEIRA bandeira) {
		this.bandeira = bandeira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoCredito other = (CartaoCredito) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	
	
}
