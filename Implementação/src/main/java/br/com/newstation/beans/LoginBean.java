package br.com.newstation.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.DocumentoDao;
import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Pedido;
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

	@Inject
	ClienteDao dao;

	@Inject
	EnderecoDao endDao;

	@Inject
	CartaoCreditoDao cardDao;

	@Inject
	DocumentoDao docDao;

	@Inject
	PedidoDao pDao;
	
	@Transactional
	public String login() {

		try {
			cliente = dao.login(cliente);
			System.out.println(cliente.getNome());

			LoginBean.id = cliente.getId();
			System.out.println(LoginBean.id);

			setStatusSessao(true);

			System.out.println("sessao: " + statusSessao);

			if (cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {

				return "/admin/admin?faces-redirect=true";
			} else {

				return "/cliente/perfil?faces-redirect=true";
			}

		} catch (Exception e) {
			return "/cliente/login?faces-redirect=true";
		}

	}

	@Transactional
	public List<Pedido> pedidos(){
		return pDao.listar(getId());
	}
	
	@Transactional
	public List<Pedido> todasPedidos(){
		return pDao.listarTudo();
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

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getCartoes().add(card);

			cardDao.salvar(cliente, card);

			return "/checkout/checkout?faces-redirect=true";
		} catch (Exception e) {

			e.printStackTrace();

			return "/carrinho/form?faces-redirect=true";
		}
	}

	@Transactional
	public String salvarCartao() {

		try {

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getCartoes().add(card);

			cardDao.salvar(cliente, card);

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

			cardDao.editar(card);

			return "/cliente/perfil?faces-redirect=true";

		} catch (Exception e) {

			return "/cliente/cartao/form?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarDocumento() {
		ValidaCPF valcpf = new ValidaCPF();

		try {

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);

			if (doc.getTipoDocumento().equals(TIPO_DOCUMENTO.CPF) && valcpf.cpfValido(doc.getCodigo())) {
				cliente.getDocumentos().add(doc);

				docDao.salvar(cliente, doc);

				return "/cliente/perfil?faces-redirect=true";
			} else if (doc.getTipoDocumento().equals(TIPO_DOCUMENTO.RG)) {

				cliente.getDocumentos().add(doc);

				docDao.salvar(cliente, doc);
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

		try {

			docDao.editar(doc);

			return "/cliente/perfil?faces-redirect=true";
		} catch (Exception e) {

			return "/cliente/documento/form?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarEnderecoCheckout() {

		try {

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getEnderecos().add(endereco);

			endDao.salvar(cliente, endereco);

			return "/checkout/checkout?faces-redirect=true";

		} catch (Exception e) {

			return "carrinho?faces-redirect=true";

		}
	}

	@Transactional
	public String salvarEndereco() {

		try {

			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getEnderecos().add(endereco);

			endDao.salvar(cliente, endereco);

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

		try {
			endDao.editar(endereco);
			return "/cliente/perfil?faces-redirect=true";

		} catch (Exception e) {

			return "/cliente/endereco/form?faces-redirect=true";

		}
	}

	@Transactional
	public String excluirCartao(CartaoCredito card) {

		cliente.setId(getId());
		cliente = dao.visualizar(cliente);

		for (CartaoCredito cards : cliente.getCartoes()) {
			if (cards.getId().equals(card.getId())) {
				cliente.getCartoes().remove(cards);
				break;
			}
		}

		cardDao.excluir(cliente, card);

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

		docDao.excluir(cliente, doc);

		return "/cliente/perfil?faces-redirect=true";

	}

	@Transactional
	public String excluirEndereco(Endereco end) {

		cliente.setId(getId());
		cliente = dao.visualizar(cliente);

		for (Endereco ends : cliente.getEnderecos()) {
			if (ends.getId().equals(end.getId())) {
				cliente.getEnderecos().remove(ends);
				break;
			}

		}

		endDao.excluir(cliente, end);

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
