package br.com.newstation.beans;

import java.util.regex.Pattern;

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

	private static boolean senhaErro = false;

	private static Cliente cliente = new Cliente();

	CriptografaSenha crp = new CriptografaSenha();

	private Integer id;

	private String senhaAtual;

	public void carregaDetalhe() {
		ClienteDao dao = new ClienteDao();
		Cliente cliente = new Cliente();
		cliente.setId(getId());
		ClienteEditarBean.cliente = dao.visualizar(cliente);

	}

	@Transactional
	public String editarCli() {

		EditarCommand cmd = new EditarCommand();
		cmd.executar(ClienteEditarBean.cliente);

		return "/cliente/perfil.xhtml?faces-redirect=true";
	}

	@Transactional
	public String atualizaSenha() {
		ClienteDao dao = new ClienteDao();

		senhaAtual = crp.criptoSenha(senhaAtual);

		if (cliente.getSenha().getSenha().equals(senhaAtual)) {

			cliente.getSenha().setSenha(crp.criptoSenha(senha.getSenha()));
		}
		dao.editar(cliente);
		return "/cliente/perfil.xhtml?faces-redirect=true";
	}

	@Transactional
	public String editar() {

		if (!senha.getSenha().equals("")) {

			if (Pattern.matches("[a-zA-Z0-9]{8,13}", senha.getSenha())) {

				senha.setSenha(crp.criptoSenha(senha.getSenha()));
				cliente.setSenha(senha);

			} else {
				senhaErro = true;
				return "/admin/cliente/editar?faces-redirect=true";
			}
		}

		EditarCommand cmd = new EditarCommand();
		cmd.executar(cliente);

		return "/admin/cliente/lista?faces-redirect=true";
	}

	public String redir(Cliente cli) {
		ClienteEditarBean.cliente = cli;

		return "/cliente/edit-form?faces-redirect=true";
	}

	public String redirAdmin(Cliente cli) {

		ClienteEditarBean.cliente = cli;

		return "/admin/cliente/editar?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {

		ClienteEditarBean.cliente = cliente;
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

	public TIPO_CLIENTE[] getTipoCliente() {

		return TIPO_CLIENTE.values();
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public boolean isSenhaErro() {
		return senhaErro;
	}

	public void setSenhaErro(boolean senhaErro) {
		ClienteEditarBean.senhaErro = senhaErro;
	}
}
