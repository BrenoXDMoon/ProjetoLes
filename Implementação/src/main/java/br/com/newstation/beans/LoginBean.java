package br.com.newstation.beans;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.command.ExcluirCommand;
import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteCartao;
import br.com.newstation.dominio.ClienteDocumento;
import br.com.newstation.dominio.ClienteEndereco;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.dominio.TIPO_DOCUMENTO;
import br.com.newstation.dominio.TIPO_ENDERECO;
import br.com.newstation.strategies.ValidaCPF;

@Model
public class LoginBean {

	@Inject
	private Cliente cliente;

	private static Integer id;
	private static boolean statusSessao = false;
	private static Set<Endereco> enderecos = new HashSet<Endereco>();
	private static Set<CartaoCredito> cartoes = new HashSet<CartaoCredito>();
	private static Set<Documento> documentos = new HashSet<Documento>();
	private static Endereco endereco = new Endereco();
	private static Documento doc = new Documento();
	private static CartaoCredito card = new CartaoCredito();
	boolean checkout = false;

	@Inject
	ClienteDao dao;

	@Inject
	PedidoDao pDao;

	public String loginCheckout() {

		checkout = true;
		return login();

	}

	@Transactional
	public String login() {

//		try {
			cliente = dao.login(cliente);
			System.out.println(cliente.getNome());

			LoginBean.id = cliente.getId();
			System.out.println(LoginBean.id);

			setStatusSessao(true);

			System.out.println("sessao: " + statusSessao);

			if (checkout) {
				System.out.println("xekout: "+ checkout);
				return "/checkout/checkout?faces-redirect=true";
			}

			if (cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {

				return "/admin/admin?faces-redirect=true";
			} else {

				return "/cliente/perfil?faces-redirect=true";
			}

//		} catch (Exception e) {
//			return "/cliente/login?faces-redirect=true";
//		}

	}

	public String logout() {

		this.cliente = new Cliente();
		setStatusSessao(false);
		setId(0);
		return "/cliente/login?faces-redirect=true";
	}

	@Transactional
	public String editarCliente() {

		try {

			this.cliente = (Cliente) dao.editar(cliente).getEntidade();

			return "/cliente/perfil?faces-redirect=true";

		} catch (Exception e) {

			return "/cliente/edit-form?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarCartaoCheckout() {

		try {

			ClienteCartao cliAux = new ClienteCartao();
			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getCartoes().add(card);

			cliAux.setCliente(cliente);
			cliAux.setCard(card);

			SalvarCommand cmd = new SalvarCommand();
			cmd.executar(cliAux);

			return "/checkout/checkout?faces-redirect=true";
		} catch (Exception e) {

			e.printStackTrace();

			return "/carrinho/form?faces-redirect=true";
		}
	}

	@Transactional
	public String salvarCartao() {

		try {

			ClienteCartao cliAux = new ClienteCartao();
			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getCartoes().add(card);
			cliAux.setCliente(cliente);
			cliAux.setCard(card);

			SalvarCommand cmd = new SalvarCommand();
			cmd.executar(cliAux);

			return "/cliente/perfil?faces-redirect=true";
		} catch (Exception e) {

			e.printStackTrace();

			return "/cliente/cartao/form?faces-redirect=true";
		}
	}

	@Transactional
	public String editarCartao() {

		try {

			System.out.println("- ENTROU");
			EditarCommand cmd = new EditarCommand();
			cmd.executar(card);

			return "/cliente/perfil?faces-redirect=true";

		} catch (Exception e) {

			return "/cliente/cartao/form?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarDocumento() {
		ValidaCPF valcpf = new ValidaCPF();

		try {

			ClienteDocumento cliAux = new ClienteDocumento();
			SalvarCommand cmd = new SalvarCommand();
			cliente.setId(getId());
			cliente = dao.visualizar(cliente);

			if (doc.getTipoDocumento().equals(TIPO_DOCUMENTO.CPF) && valcpf.cpfValido(doc.getCodigo())) {
				cliente.getDocumentos().add(doc);
				cliAux.setCliente(cliente);
				cliAux.setDoc(doc);
				cmd.executar(cliAux);

				return "/cliente/perfil?faces-redirect=true";
			} else if (doc.getTipoDocumento().equals(TIPO_DOCUMENTO.RG)) {

				cliente.getDocumentos().add(doc);
				cliAux.setCliente(cliente);
				cliAux.setDoc(doc);
				cmd.executar(cliAux);
				return "/cliente/perfil?faces-redirect=true";

			} else {
				return "/cliente/documento/form?faces-redirect=true";
			}

		} catch (Exception e) {

			return "/cliente/documento/form?faces-redirect=true";

		}
	}

	@Transactional
	public String editarDocumento() {

		EditarCommand cmd = new EditarCommand();

		try {
			cmd.executar(doc);

			return "/cliente/perfil?faces-redirect=true";
		} catch (Exception e) {

			return "/cliente/documento/form?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarEnderecoCheckout() {

		SalvarCommand cmd = new SalvarCommand();
		ClienteEndereco cliAux = new ClienteEndereco();
		try {

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getEnderecos().add(endereco);
			cliAux.setCliente(cliente);
			cliAux.setEndereco(endereco);

			cmd.executar(cliAux);

			return "/checkout/checkout?faces-redirect=true";

		} catch (Exception e) {

			return "carrinho?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarEndereco() {

		SalvarCommand cmd = new SalvarCommand();
		ClienteEndereco cliAux = new ClienteEndereco();

		try {

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getEnderecos().add(endereco);
			cliAux.setCliente(cliente);
			cliAux.setEndereco(endereco);

			cmd.executar(cliAux);

			return "/cliente/perfil?faces-redirect=true";

		} catch (Exception e) {

			return "/cliente/endereco/form?faces-redirect=true";

		}
	}

	public String redirCartao(CartaoCredito card) {

		LoginBean.card = card;
		return "/cliente/cartao/edit-form?faces-redirect=true";

	}

	public String redirDocumento(Documento doc) {

		LoginBean.doc = doc;

		return "/cliente/documento/edit-form?faces-redirect=true";

	}

	public String redirEndereco(Endereco end) {

		LoginBean.endereco = end;
		return "/cliente/endereco/edit-form?faces-redirect=true";

	}

	@Transactional
	public String editarEndereco() {

		EditarCommand cmd = new EditarCommand();

		try {

			cmd.executar(endereco);
			return "/cliente/perfil?faces-redirect=true";

		} catch (Exception e) {

			return "/cliente/endereco/form?faces-redirect=true";

		}
	}

	@Transactional
	public String excluirCartao(CartaoCredito card) {

		ClienteCartao cliAux = new ClienteCartao();
		ExcluirCommand cmd = new ExcluirCommand();
		cliente.setId(getId());
		cliente = dao.visualizar(cliente);

		for (CartaoCredito cards : cliente.getCartoes()) {
			if (cards.getId().equals(card.getId())) {
				cliente.getCartoes().remove(cards);
				break;
			}
		}

		cliAux.setCliente(cliente);
		cliAux.setCard(card);
		cmd.executar(cliAux);

		return "/cliente/perfil?faces-redirect=true";
	}

	@Transactional
	public String excluirDocumento(Documento doc) {

		cliente.setId(getId());
		cliente = dao.visualizar(cliente);

		for (Documento docs : cliente.getDocumentos()) {
			if (docs.getId().equals(doc.getId())) {
				cliente.getDocumentos().remove(docs);
				break;
			}

		}

		ExcluirCommand cmd = new ExcluirCommand();
		ClienteDocumento cliAux = new ClienteDocumento();
		cliAux.setCliente(cliente);
		cliAux.setDoc(doc);

		cmd.executar(cliAux);

		return "/cliente/perfil?faces-redirect=true";

	}

	@Transactional
	public String excluirEndereco(Endereco end) {

		ExcluirCommand cmd = new ExcluirCommand();
		ClienteEndereco cliAux = new ClienteEndereco();

		cliente.setId(getId());
		cliente = dao.visualizar(cliente);

		for (Endereco ends : cliente.getEnderecos()) {
			if (ends.getId().equals(end.getId())) {
				cliente.getEnderecos().remove(ends);
				break;
			}

		}
		cliAux.setCliente(cliente);
		cliAux.setEndereco(end);
		cmd.executar(cliAux);

		return "/cliente/perfil?faces-redirect=true";

	}

	public void carregar() {

		cliente.setId(getId());

		cliente = dao.visualizar(cliente);
		setEnderecos(cliente.getEnderecos());
		setCartoes(cliente.getCartoes());
		setDocumentos(cliente.getDocumentos());
		endereco = new Endereco();
		doc = new Documento();
		card = new CartaoCredito();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		LoginBean.id = id;
	}

	public boolean listaVazia() {

		boolean as;

		if (cliente.getCartoes().isEmpty()) {
			as = true;
		} else {
			as = false;
		}

		return as;
	}

	public boolean isStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(boolean statusSessao) {
		LoginBean.statusSessao = statusSessao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		LoginBean.endereco = endereco;
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		LoginBean.doc = doc;
	}

	public CartaoCredito getCard() {
		return card;
	}

	public void setCard(CartaoCredito card) {
		LoginBean.card = card;
	}

	public BANDEIRA[] getBandeiras() {
		return BANDEIRA.values();
	}

	public TIPO_ENDERECO[] getTipoEndereco() {
		return TIPO_ENDERECO.values();
	}

	public TIPO_DOCUMENTO[] getTipoDocumento() {
		return TIPO_DOCUMENTO.values();
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		LoginBean.enderecos = enderecos;
	}

	public Set<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public Set<Documento> getDocumentos() {
		return documentos;
	}

	public void setCartoes(Set<CartaoCredito> cartoes) {
		LoginBean.cartoes = cartoes;
	}

	public void setDocumentos(Set<Documento> docs) {
		LoginBean.documentos = docs;
	}

}
