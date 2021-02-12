package br.com.newstation.vh;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.newstation.commands.EditarCommand;
import br.com.newstation.commands.ExcluirCommand;
import br.com.newstation.commands.ICommand;
import br.com.newstation.commands.ListarCommand;
import br.com.newstation.commands.SalvarCommand;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Endereco;

@Model
public class ClienteBean {

	@Inject
	private HttpServletRequest request;

	private ICommand cmd;
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();


	@Transactional
	public String salvar() {
		
		cmd = new SalvarCommand();

		List<Endereco> listaEnd = new ArrayList<Endereco>();

		//listaEnd.add(endereco);

		//cliente.setEnderecos(listaEnd);
		
		cmd.executar(cliente);
		
		System.out.println("SUCESSO!!!");
		
		return "/cliente/perfil.xhtml?faces-redirect=true";
	}

	public List<Cliente> listar() {

		cmd = new ListarCommand();

		return null;
	}

	public String editar() {
		
		cmd = new EditarCommand();
		
		request.getSession().setAttribute("cliente", cmd.executar(cliente).getEntidade());
		
		return "../perfil.xhtml?faces-redirect=true";
	}

	public String excluir() {
		
		cmd = new ExcluirCommand();

		return "";
	}

	public String logout() {
		request.getSession().invalidate();

		return "index.xhtml?faces-redirect=true";
	}

	public ICommand getCmd() {
		return cmd;
	}

	public void setCmd(ICommand cmd) {
		this.cmd = cmd;
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
