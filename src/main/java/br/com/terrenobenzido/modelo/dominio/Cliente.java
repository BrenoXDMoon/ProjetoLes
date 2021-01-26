package br.com.terrenobenzido.modelo.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
public class Cliente extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@NotBlank
	private String nomeCompleto;

	@NotBlank
	private String cpf;

	@NotBlank
	private String dataNascimento;

	@NotBlank
	@Email
	private String email;

	@Enumerated
	private TIPO_CLIENTE tipoCli;

	@NotBlank
	private String senha;

	@OneToMany
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	@OneToMany
	private List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();

	private boolean ativo;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<SystemRoles> roles = new ArrayList<SystemRoles>();

	private String funcao;

	public Cliente() {

	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;

	}

	public TIPO_CLIENTE getTipoCli() {
		return tipoCli;
	}

	public void setTipoCli(TIPO_CLIENTE tipoCli) {
		this.tipoCli = tipoCli;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
}
