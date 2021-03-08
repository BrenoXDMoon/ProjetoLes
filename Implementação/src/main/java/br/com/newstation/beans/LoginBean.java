package br.com.newstation.beans;

import java.util.HashSet;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.DocumentoDao;
import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.dominio.TIPO_DOCUMENTO;
import br.com.newstation.dominio.TIPO_ENDERECO;

@Model
public class LoginBean {

	private Cliente cliente = new Cliente();
	private static Integer id;
	private static boolean statusSessao;
	private Endereco endereco = new Endereco();
	private Documento doc = new Documento();
	private CartaoCredito card = new CartaoCredito();
	
	@Inject
	ClienteDao dao;
	
	@Inject
	EnderecoDao endDao;
	
	@Inject
	CartaoCreditoDao cardDao;
	
	@Inject
	DocumentoDao docDao;
	
	public LoginBean() {
		setStatusSessao(false);
	}
	
	@Transactional
	public String salvarCartao(){
		
		try {
			
			System.out.println("- ENTROU");
			
			cardDao.salvar(card);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String editarCartao(){
		
		try {
			
			System.out.println("- ENTROU");
			
			cardDao.editar(card);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String salvarDocumento(){
		
		try {
			
			System.out.println("- ENTROU");
			
			docDao.salvar(doc);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String editarDocumento(){
		
		try {
			
			System.out.println("- ENTROU");
			
			docDao.editar(doc);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String salvarEndereco(){
		
		try {
			
			Cliente cli = this.cliente;
			
			cli.setEnderecos(new HashSet<Endereco>());
			
			cli.getEnderecos().add(endereco);
			
			endDao.salvar(cli);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	
	@Transactional
	public String editarEndereco(){
		
		try {
			
			Cliente cli = this.cliente;
			
			cli.setEnderecos(new HashSet<Endereco>());
			
			cli.getEnderecos().add(endereco);
			
			endDao.editar(cli);
			
			return "cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String login() {
		
		try {
			this.cliente = dao.login(cliente);
			
			this.id = cliente.getId();
			
			this.setStatusSessao(true);
			
			if(this.cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {
				
				return "/admin/admin?faces-redirect=true";
			}else {
				
				return "/cliente/perfil?faces-redirect=true";
			}
			
		}catch (Exception e) {
			return "/cliente/login?faces-redirect=true";
		}
		
	}
	
	@Transactional
	public String logout() {
		
		this.cliente = new Cliente();
		this.setStatusSessao(false);
		return "/cliente/login?faces-redirect=true";
	}
	
	public void carregar() {
		
		this.cliente.setId(getId());
		
		this.cliente = dao.visualizar(cliente);
		
		System.out.println("- Carregando o cara na tela");
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
		this.id = id;
	}
	
	public boolean listaVazia() {
		
		boolean as;
		
		if(this.cliente.getCartoes().isEmpty()) {
			as = true;
		}else {
			as = false;
		}
		
		return as;
	}

	public static boolean isStatusSessao() {
		return statusSessao;
	}

	public static void setStatusSessao(boolean statusSessao) {
		LoginBean.statusSessao = statusSessao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}
	
	public CartaoCredito getCard() {
		return card;
	}

	public void setCard(CartaoCredito card) {
		this.card = card;
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
	
}
