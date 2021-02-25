package br.com.newstation.vh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cidade;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Estado;
import br.com.newstation.dominio.Senha;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.dominio.TIPO_DOCUMENTO;
import br.com.newstation.dominio.TIPO_ENDERECO;

@Model
public class ClienteSalvarBean {
	
	@Inject
	private ClienteDao dao;
	
	private Cliente cliente = new Cliente();
	
	private Endereco endereco = new Endereco();
	
	private CartaoCredito cartao = new CartaoCredito();
	
	private Senha senha = new Senha();
	
	private Cidade cidade = new Cidade();
	
	private Estado estado = new Estado();
	
	private Documento documento = new Documento();
	
	private String dataNascimento;
	
	private String validade;
	
	@Transactional
	public String salvar() throws ParseException{
		
		if(senha.getConfirmaSenha().equals(senha.getSenha())) {
			System.out.println("- SENHA VALIDADA NA BEAN");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			cliente.setSenha(senha);
			
			cidade.setEstado(estado);
			
			endereco.setCidade(cidade);
			
			cliente.setTipoCliente(TIPO_CLIENTE.Basico);
			
			documento.setValidade(LocalDate.parse(dataNascimento,formatter));
			
			cliente.getDocumentos().add(documento);
			
			cliente.getEnderecos().add(endereco);
			cliente.getCartoes().add(cartao);
			
			cliente.setDtCadastro(LocalDate.now());
			
			
			cliente.setDataNascimento(LocalDate.parse(validade,formatter));
		
			SalvarCommand cmd = new SalvarCommand();
		
			this.cliente = (Cliente) cmd.executar(cliente).getEntidade();
		
			return "/cliente/perfil.xhtml?faces-redirect=true";
		}else {
			return "";
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
		
	}
	
	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ClienteDao getDao() {
		return dao;
	}

	public void setDao(ClienteDao dao) {
		this.dao = dao;
	}
	
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public TIPO_ENDERECO[] getTipos() {
		return TIPO_ENDERECO.values();
	}

	public BANDEIRA[] getBandeiras() {
		return BANDEIRA.values();
	}
	
	public TIPO_DOCUMENTO[] getDocumentos() {
		return TIPO_DOCUMENTO.values();
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}
}
