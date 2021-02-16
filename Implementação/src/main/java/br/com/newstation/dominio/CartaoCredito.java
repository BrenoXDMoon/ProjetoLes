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
	private Integer id;
	
	private String numero;
	private String nomeImpresso;
	private String codigoSeguranca;
	
	@Enumerated(EnumType.STRING)
	private BANDEIRA bandeira;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNomeImpresso() {
		return nomeImpresso;
	}
	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}
	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}
	public BANDEIRA getBandeira() {
		return bandeira;
	}
	public void setBandeira(BANDEIRA bandeira) {
		this.bandeira = bandeira;
	}
	
	
	
}
