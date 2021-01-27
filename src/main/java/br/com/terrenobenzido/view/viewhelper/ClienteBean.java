package br.com.terrenobenzido.view.viewhelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.terrenobenzido.modelo.dao.ClienteDAO;
import br.com.terrenobenzido.modelo.dao.EnderecoDAO;
import br.com.terrenobenzido.modelo.dominio.CartaoCredito;
import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.modelo.dominio.Endereco;
import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.TIPO_CLIENTE;
import br.com.terrenobenzido.view.command.ICommand;
import br.com.terrenobenzido.view.command.ListarCommand;

@Model
public class ClienteBean {

	@Inject
	private HttpServletRequest request;

	private ICommand cmd;

	@Inject
	private ClienteDAO dao = new ClienteDAO();

	private Cliente cliente = new Cliente();

	private Endereco endereco = new Endereco();

	private CartaoCredito cartao = new CartaoCredito();

	@Transactional
	public String salvar() {

		Set<Endereco> listaEnd = new HashSet<Endereco>();
		Set<CartaoCredito> listaCard = new HashSet<CartaoCredito>();

		listaEnd.add(endereco);
		listaCard.add(cartao);

		cliente.setEnderecos(listaEnd);
		cliente.setCartoes(listaCard);

		cliente.setFuncao("Usuario");
		cliente.setAtivo(true);
		cliente.setTipoCli(TIPO_CLIENTE.Basico);

		cliente = (Cliente) dao.salvar(cliente).getEntidades().get(0);
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("status", false);
		
		session.setAttribute("cliente", cliente);
		
		return "/cliente/perfil.xhtml?faces-redirect=true";
	}
	
	@Transactional
	public Cliente login(String user, String pwd) {
		EnderecoDAO endDao = new EnderecoDAO();
		Cliente clienteS = dao.login(user, pwd);
		List<Endereco> listEnd = new ArrayList<Endereco>();
		
		if (clienteS != null) {

			return clienteS;
		}
		else {
			return null;
		}
	}

	public List<Cliente> listarClientes() {

		cmd = new ListarCommand();

		return converteListaClientes(cmd.executar(cliente).getEntidades());
	}

	public String editarCliente() {

		return "";
	}

	public String excluirCliente() {

		cliente.setAtivo(false);
		
		dao.excluir(cliente);
		
		return "";
	}

	public boolean hasRole(String name) {
		return getRequest().isUserInRole(name);
	}

	public String logout() {
		request.getSession().invalidate();

		return "index.xhtml?faces-redirect=true";
	}
	
	public void logoutInterno() {
		request.getSession().invalidate();
	}

	private List<Cliente> converteListaClientes(List<EntidadeDominio> lista) {

		List<Cliente> listaCli = new ArrayList<Cliente>();

		for (EntidadeDominio ent : listaCli) {
			Cliente cli = (Cliente) ent;
			listaCli.add(cli);
		}

		return listaCli;
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

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {

		this.cartao = cartao;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	

}
