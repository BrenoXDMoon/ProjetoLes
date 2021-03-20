package br.com.newstation.dominio;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;


@Entity
public class Cliente extends EntidadeDominio{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TIPO_CLIENTE tipoCliente;
	
	@Column
	private String nome;
	
	@Column
	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	@Embedded
	private Senha senha = new Senha();

	@Column
	private boolean ativo;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Endereco> enderecos = new HashSet<Endereco>();

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Documento> documentos = new HashSet<Documento>();

	@OneToMany(fetch = FetchType.EAGER)
	private Set<CartaoCredito> cartoes = new HashSet<CartaoCredito>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}
	
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Set<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TIPO_CLIENTE getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TIPO_CLIENTE tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(Set<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}
}
