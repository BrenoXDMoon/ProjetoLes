package br.com.newstation.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Senha;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.seguranca.CriptografaSenha;

@Model
public class ClienteEditarBean {

	private Senha senha = new Senha();

	private static Cliente cliente = new Cliente();
	
	CriptografaSenha crp = new CriptografaSenha();
	
	private Integer id;

	private String dataNascimento;
	
	public void carregaDetalhe() {
		
		ClienteDao dao = new ClienteDao();
		cliente =  dao.visualizar(cliente);
		
		dataNascimento = cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
		
		//senha = cliente.getSenha();
	}
	
	@Transactional
	public String editar() {

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		cliente.setDataNascimento(LocalDate.parse(dataNascimento, formatter));
		
		if (senha != null) {
			
			senha.setSenha(crp.criptoSenha(senha.getSenha()));
			cliente.setSenha(senha);
			
		}


		EditarCommand cmd = new EditarCommand();
		cmd.executar(cliente);
		
		return "/admin/cliente/lista?faces-redirect=true";
	}

	public String redir() {
		return "/cliente/editar?faces-redirect=true";
	}
	
	public String redirAdmin(Cliente cli) {
		
		this.cliente = cli;
		
		return "/admin/cliente/editar?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public TIPO_CLIENTE[] getTipoCliente(){
		
		return TIPO_CLIENTE.values();
	}
}
