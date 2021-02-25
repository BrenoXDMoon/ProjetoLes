package br.com.newstation.vh;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cidade;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Estado;
import br.com.newstation.dominio.Senha;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.dominio.TIPO_DOCUMENTO;
import br.com.newstation.dominio.TIPO_ENDERECO;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidaExistenciaClientePorCPF;

@Model
public class ClienteSalvarBean {
	
	@Inject
	private ClienteDao dao;
	
	private Cliente cliente = new Cliente();
	
	private Endereco endereco = new Endereco();
	
	private CartaoCredito cartao = new CartaoCredito();
	
	private Senha senha = new Senha();
	
	private Cidade cidade = new Cidade();
	
	private Estado estado = new Estado();
	
	private Documento documento = new Documento();
	
	@Transactional
	public String salvar(){
		
		if(senha.getConfirmaSenha().equals(senha.getSenha())) {
			System.out.println("- SENHA VALIDADA NA BEAN");
			
			cliente.setSenha(senha);
			
			cidade.setEstado(estado);
			
			endereco.setCidade(cidade);
			
			cliente.setTipoCliente(TIPO_CLIENTE.Basico);
			
			cliente.getDocumentos().add(documento);
			
			cliente.getEnderecos().add(endereco);
			cliente.getCartoes().add(cartao);
			
			
			if(executarRegrasSalvar(documento).equals(null)) {
				
				dao.salvar(cliente);
				return "/cliente/perfil?faces-redirect=true";
				
			}else {
								
				return "/cliente/login?faces-redirect=true";
				
			}		
		}else {
			
			return "";
			
		}
	}	

	private String executarRegrasSalvar(Documento doc) {
		
		String retorno = null;	
		
		if(doc.getTipoDocumento().equals(TIPO_DOCUMENTO.CPF)) {
			ValidaCPF validaCpf = new ValidaCPF();
			
			ValidaExistenciaClientePorCPF validaExist = new ValidaExistenciaClientePorCPF();
			
			retorno = validaCpf.processar(doc) + validaExist.processar(doc);	
		}
		
		
		return retorno;
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

	public BANDEIRA[] getBandeiras() {
		return BANDEIRA.values();
	}
	
	public TIPO_DOCUMENTO[] getDocumentos() {
		return TIPO_DOCUMENTO.values();
	}
}
