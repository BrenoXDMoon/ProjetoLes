package br.com.newstation.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

@Entity
public class Cliente extends Pessoa{

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
	
	@Column
	private LocalDate dataNascimento;

	@Embedded
	private Senha senha;
	
	
	@OneToMany
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@OneToMany
	private List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
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
}
