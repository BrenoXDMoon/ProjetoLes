package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.dominio.BANDEIRA;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.dominio.TIPO_CLIENTE;
import br.com.newstation.dominio.TIPO_DOCUMENTO;
import br.com.newstation.dominio.TIPO_ENDERECO;
import br.com.newstation.seguranca.CriptografaSenha;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidaExistenciaPorEmail;

@Model
public class ClienteSalvarBean {

	private Cliente cliente = new Cliente();

	private Endereco endereco = new Endereco();

	private Documento documento = new Documento();

	private CartaoCredito cartao = new CartaoCredito();

	private String dataNascimento;

	private static boolean emailError = false;

	private static boolean cpfError = false;

	@Inject
	private ClienteDao dao;

	@Transactional
	public String salvar() {

		if (cliente.getSenha().getConfirmaSenha().equals(cliente.getSenha().getSenha())) {

			CriptografaSenha crp = new CriptografaSenha();

			cliente.getSenha().setSenha(crp.criptoSenha(cliente.getSenha().getSenha()));

			cliente.setAtivo(true);

			cliente.setTipoCliente(TIPO_CLIENTE.Basico);

			cliente.getDocumentos().add(documento);

			cliente.getEnderecos().add(endereco);

			cliente.getCartoes().add(cartao);
			
			SalvarCommand cmd = new SalvarCommand();
//			System.out.println("Entra command");
			Resultado resultado = cmd.executar(cliente);
			
			this.cliente = (Cliente) resultado.getEntidade();
			
			if(resultado.getMensagem() == null) {
				
				LoginBean lb = new LoginBean();
				lb.setId(this.cliente.getId());
				
				return "/cliente/perfil?faces-redirect=true";
			}else {
				return "/cliente/form?faces-redirect=true";
			}
			
		} else {

			return "/cliente/form?faces-redirect=true";

		}
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

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public TIPO_ENDERECO[] getTipos() {
		return TIPO_ENDERECO.values();
	}

	public TIPO_DOCUMENTO[] getDocumentos() {
		return TIPO_DOCUMENTO.values();
	}

	public BANDEIRA[] getBandeiras() {
		return BANDEIRA.values();
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}

	public boolean isEmailError() {
		return emailError;
	}

	public void setEmailError(boolean emailError) {
		ClienteSalvarBean.emailError = emailError;
	}

	public boolean isCpfError() {
		return cpfError;
	}

	public void setCpfError(boolean cpfError) {
		ClienteSalvarBean.cpfError = cpfError;
	}
}
