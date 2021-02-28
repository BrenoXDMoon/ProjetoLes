package br.com.newstation.vh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Senha;

@Model
public class ClienteEditarBean {

	private Senha senha = new Senha();

	private Cliente cliente = new Cliente();

	private Documento documento = new Documento();

	private String dataNascimento;

	
	public void listar() {
		ClienteListarBean lisb = new ClienteListarBean();

		cliente = lisb.getClientes().get(0);
		Object[] arrayDoc = cliente.getDocumentos().toArray();
		documento = (Documento) arrayDoc[0];
		dataNascimento = cliente.getDataNascimento().toString();
		senha = cliente.getSenha();
		
	}
	
	@Transactional
	public String Editar() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		cliente.setDataNascimento(LocalDate.parse(dataNascimento, formatter));
		cliente.setSenha(senha);


		EditarCommand cmd = new EditarCommand();
		cmd.executar(cliente);
		
		return "/index?faces-redirect=true";
		//return "/cliente/perfil?faces-redirect=true";
	}

	public String redir() {
		return "/cliente/alteraCliente?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
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
}
