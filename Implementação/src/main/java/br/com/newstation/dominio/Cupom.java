package br.com.newstation.dominio;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	
	private BigDecimal preco;
	
	@Lob
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TIPO_CUPOM tipoCupom;
	
	
	public TIPO_CUPOM getTipoCupom() {
		return tipoCupom;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setTipoCupom(TIPO_CUPOM tipoCupom) {
		this.tipoCupom = tipoCupom;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}