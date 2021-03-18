package br.com.newstation.beans;

import java.util.HashSet;
import java.util.Set;

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

	@Inject
	private Cliente cliente;
	
	private static Integer id;
	private static boolean statusSessao;
	private static Set<Endereco> enderecos = new HashSet<Endereco>();
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
	
	public LoginBean() {
		setStatusSessao(false);
	}
	
	@Transactional
	public String salvarCartao(){
		
		try {
			
			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getCartoes().add(card);
			
			cardDao.salvar(cliente);
			
			return "/cliente/perfil?faces-redirect=true";
		}catch (Exception e) {
			
			e.printStackTrace();
			
			return "/cliente/cartao/form?faces-redirect=true";
		}
	}
	
	@Transactional
	public String editarCartao(){
		
		try {
			
			System.out.println("- ENTROU");
			
			cardDao.editar(card);
			
			return "/cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "/cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String salvarDocumento(){
		
		try {
			
			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getDocumentos().add(doc);
			
			docDao.salvar(cliente);
			
			return "/cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "/cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String editarDocumento(Documento doc){
		
		try {
			
			System.out.println("- ENTROU");
			
			docDao.editar(doc);
			
			return "/cliente/perfil?faces-redirect=true";
		}catch (Exception e) {
			
			return "/cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String salvarEndereco(){
		
		try {
			
			cliente.setId(getId());
			cliente = dao.visualizar(cliente);
			cliente.getEnderecos().add(endereco);
			
			
			endDao.salvar(cliente);
			
			return "/cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "/cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	public String redirCartao(CartaoCredito card){
		LoginBean.card = card;
		return "/cliente/cartao/edit-form?faces-redirect=true";
	}
	
	public String redirDocumento(Documento doc){
		LoginBean.doc = doc;
		return "/cliente/documento/edit-form?faces-redirect=true";
	}
	
	public String redirEndereco(Endereco end){
		LoginBean.endereco = end;
		return "/cliente/endereco/edit-form?faces-redirect=true";
	}
	
	@Transactional
	public String editarCliente(){
		
		try {
			dao.editar(cliente);
			return "/cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "/cliente/edit-form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public String editarEndereco(){
		
		try {
			endDao.editar(endereco);
			return "/cliente/perfil?faces-redirect=true";
			
		}catch (Exception e) {
			
			return "/cliente/endereco/form?faces-redirect=true";
			
		}
	}
	
	@Transactional
	public  String excluirCartao(CartaoCredito card){
		
		cliente.setId(getId());
		cliente = dao.visualizar(cliente);
		
		for(CartaoCredito cards : cliente.getCartoes()) {
			if(cards.getId().equals(card.getId())) {
				cliente.getCartoes().remove(cards);
				break;
			}	
		}
		
		cardDao.excluir(cliente, card);
		
		return "/cliente/perfil?faces-redirect=true";
	}
	
	@Transactional
	public  String excluirDocumento(Documento doc){
		
		cliente.setId(getId());
		cliente = dao.visualizar(cliente);
		
		for(Documento docs : cliente.getDocumentos()) {
			if(docs.getId().equals(doc.getId())) {
				cliente.getDocumentos().remove(docs);
				break;
			}
				
		}
		
		
		docDao.excluir(cliente, doc);
		
		return "/cliente/perfil?faces-redirect=true";

	}
	
	@Transactional
	public  String excluirEndereco(Endereco end){
		
		cliente.setId(getId());
		cliente = dao.visualizar(cliente);
		
		for(Endereco ends:cliente.getEnderecos()) {
			if(ends.getId().equals(end.getId())) {
				cliente.getEnderecos().remove(ends);
				break;
			}
				
		}
		
		
		endDao.excluir(cliente, end);
		
		return "/cliente/perfil?faces-redirect=true";

	}
	
	@Transactional
	public String login() {
		
		try {
			cliente = dao.login(cliente);
			
			LoginBean.id = cliente.getId();
			
			setStatusSessao(true);
			
			if(cliente.getTipoCliente().equals(TIPO_CLIENTE.Admin)) {
				
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
		
		cliente = new Cliente();
		setStatusSessao(false);
		return "/cliente/login?faces-redirect=true";
	}
	
	public void carregar() {
		
		cliente.setId(getId());
		
		cliente = dao.visualizar(cliente);
		setEnderecos(cliente.getEnderecos());
		
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		cliente = cliente;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		LoginBean.id = id;
	}
	
	public boolean listaVazia() {
		
		boolean as;
		
		if(cliente.getCartoes().isEmpty()) {
			as = true;
		}else {
			as = false;
		}
		
		return as;
	}

	public  boolean isStatusSessao() {
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

	public  Set<Endereco> getEnderecos() {
		return  enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		LoginBean.enderecos = enderecos;
	}
	
}
