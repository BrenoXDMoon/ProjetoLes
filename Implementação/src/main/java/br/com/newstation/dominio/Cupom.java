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

	@Override
	public String toString() {
		 return getId().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Cupom other = (Cupom) obj;
		if(other.getId().equals(id)) {
			return true;
		}
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}