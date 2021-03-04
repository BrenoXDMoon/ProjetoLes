package br.com.newstation.vh;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cidade;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Estado;
import br.com.newstation.dominio.Resultado;
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
	
	private Senha senha = new Senha();
	
	private Cidade cidade = new Cidade();
	
	private Estado estado = new Estado();
	
	private Documento documento = new Documento();
	
	private String dataNascimento;
	
	private String validade;
	
	
	@Transactional
	public String salvar() throws ParseException{
		
		if(senha.getConfirmaSenha().equals(senha.getSenha())) {
			
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			cliente.setSenha(senha);
			
			cliente.setAtivo(true);
			
			cidade.setEstado(estado);
			
			endereco.setCidade(cidade);

			cliente.setTipoCliente(TIPO_CLIENTE.Basico);
			
			documento.setValidade(LocalDate.parse(validade,formatter));
			
			cliente.getDocumentos().add(documento);
			
			cliente.getEnderecos().add(endereco);
			
			cliente.setDtCadastro(LocalDate.now());

			cliente.setDataNascimento(LocalDate.parse(dataNascimento,formatter));
		
			SalvarCommand cmd = new SalvarCommand();

			Resultado resultado = cmd.executar(cliente);
			
			this.cliente = (Cliente) resultado.getEntidade();
			
			if(resultado.getMensagem() == null) {
				
				
				
				return "/cliente/perfil?faces-redirect=true";
				
			}else {
				
				return "/cliente/addCliente?faces-redirect=true";
				
			}
		}else {
			
			return "/cliente/addCliente?faces-redirect=true";
			
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
