package br.com.newstation.dominio;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Documento extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	
	@Temporal(TemporalType.DATE)
	private Calendar validade;
	
	@Enumerated(EnumType.STRING)
	private TIPO_DOCUMENTO tipoDocumento;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public TIPO_DOCUMENTO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TIPO_DOCUMENTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
