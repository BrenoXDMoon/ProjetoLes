package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.CartaPedidoDao;
import br.com.newstation.daos.CartaoCreditoDao;
import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.CupomDao;
import br.com.newstation.daos.DocumentoDao;
import br.com.newstation.daos.EnderecoDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.daos.IDao;
import br.com.newstation.daos.PedidoDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.CartaPedido;
import br.com.newstation.dominio.CartaoCredito;
import br.com.newstation.dominio.CartaoPedido;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteCartao;
import br.com.newstation.dominio.ClienteDocumento;
import br.com.newstation.dominio.ClienteEndereco;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.PasseLivre;
import br.com.newstation.strategies.ValidaCPF;
import br.com.newstation.strategies.ValidaEstoque;
import br.com.newstation.strategies.ValidaExistenciaClientePorCPF;
import br.com.newstation.strategies.ValidaExistenciaPorEmail;

public class Fachada implements IFachada {

	private Map<String, IDao> daos;

	// Mapa Macro, com TODAS as regras de negocio
	private Map<String, Map<String, List<IStrategy>>> regrasNegocio;

	private StringBuilder sb = new StringBuilder();
	private Resultado resultado;

	public Fachada() {

		daos = new HashMap<String, IDao>();

		daos.put(Cliente.class.getName(), new ClienteDao());
		daos.put(ClienteDocumento.class.getName(), new DocumentoDao());
		daos.put(ClienteEndereco.class.getName(), new EnderecoDao());
		daos.put(ClienteCartao.class.getName(), new CartaoCreditoDao());
		daos.put(Documento.class.getName(), new DocumentoDao());
		daos.put(Endereco.class.getName(), new EnderecoDao());
		daos.put(CartaoCredito.class.getName(), new CartaoCreditoDao());
		daos.put(CartaoPedido.class.getName(), new CartaPedidoDao());
		daos.put(Cupom.class.getName(), new CupomDao());
		daos.put(Carta.class.getName(), new CartaDao());
		daos.put(CartaPedido.class.getName(), new CartaPedidoDao());
		daos.put(Estoque.class.getName(), new EstoqueDao());
		daos.put(Pedido.class.getName(), new PedidoDao());

		regrasNegocio = new HashMap<String, Map<String, List<IStrategy>>>();

	// --------------------- Hash Cliente ------------------------------//

		// Criando lista de RNs do Cliente Salvar
		List<IStrategy> rnsClienteSalvar = new ArrayList<IStrategy>();

		rnsClienteSalvar.add(new ValidaCPF());
		rnsClienteSalvar.add(new ValidaExistenciaClientePorCPF());
		rnsClienteSalvar.add(new ValidaExistenciaPorEmail());

		// Criando lista de RNs do Cliente Alterar
		List<IStrategy> rnsClienteEditar = new ArrayList<IStrategy>();
		rnsClienteEditar.add(new ValidaCPF());

		// mapa de Operacoes Cliente, RNs da operacao de Cliente.
		Map<String, List<IStrategy>> mapaCliente = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de cliente
		mapaCliente.put("SALVAR", rnsClienteSalvar);
		mapaCliente.put("EDITAR", rnsClienteEditar);

	// --------------------- Hash Estoque ------------------------------//

		// Criando lista de RNs do Estoque Salvar
		List<IStrategy> rnsEstoqueSalvar = new ArrayList<IStrategy>();

		rnsEstoqueSalvar.add(new PasseLivre());

		// Criando lista de RNs do Estoque Alterar
		List<IStrategy> rnsEstoqueEditar = new ArrayList<IStrategy>();

		rnsEstoqueEditar.add(new ValidaEstoque());

		// mapa de Operacoes Cliente, RNs da operacao de Estoque.
		Map<String, List<IStrategy>> mapaEstoque = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de Estoque
		mapaEstoque.put("SALVAR", rnsEstoqueSalvar);
		mapaEstoque.put("EDITAR", rnsEstoqueEditar);

	// --------------------- Hash ClienteDocumento ------------------------------//

		// Criando lista de RNs do ClienteDocumento Salvar
		List<IStrategy> rnsClienteDocumentoSalvar = new ArrayList<IStrategy>();

		rnsClienteDocumentoSalvar.add(new PasseLivre());

		// Criando lista de RNs do ClienteDocumento Alterar
		List<IStrategy> rnsClienteDocumentoEditar = new ArrayList<IStrategy>();

		rnsClienteDocumentoEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de ClienteDocumento.
		Map<String, List<IStrategy>> mapaClienteDocumento = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de ClienteDocumento
		mapaClienteDocumento.put("SALVAR", rnsClienteDocumentoSalvar);
		mapaClienteDocumento.put("EDITAR", rnsClienteDocumentoEditar);
	// --------------------- Hash Carta ------------------------------//

		// Criando lista de RNs do Carta Salvar
		List<IStrategy> rnsCartaSalvar = new ArrayList<IStrategy>();

		rnsCartaSalvar.add(new PasseLivre());

		// Criando lista de RNs do Carta Alterar
		List<IStrategy> rnsCartaEditar = new ArrayList<IStrategy>();

		rnsCartaEditar.add(new ValidaEstoque());

		// mapa de Operacoes Cliente, RNs da operacao de Carta.
		Map<String, List<IStrategy>> mapaCarta = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de Carta
		mapaCarta.put("SALVAR", rnsCartaSalvar);
		mapaCarta.put("EDITAR", rnsCartaEditar);
	// --------------------- Hash Cupom ------------------------------//

		// Criando lista de RNs do Cupom Salvar
		List<IStrategy> rnsCupomSalvar = new ArrayList<IStrategy>();

		rnsCupomSalvar.add(new PasseLivre());

		// Criando lista de RNs do Cupom Alterar
		List<IStrategy> rnsCupomEditar = new ArrayList<IStrategy>();

		rnsCupomEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de Cupom.
		Map<String, List<IStrategy>> mapaCupom = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de Cupom
		mapaCupom.put("SALVAR", rnsCupomSalvar);
		mapaCupom.put("EDITAR", rnsCupomEditar);
	// --------------------- Hash ClienteCartao ------------------------------//

		// Criando lista de RNs do ClienteCartao Salvar
		List<IStrategy> rnsClienteCartaoSalvar = new ArrayList<IStrategy>();

		rnsClienteCartaoSalvar.add(new PasseLivre());

		// Criando lista de RNs do ClienteCartao Alterar
		List<IStrategy> rnsClienteCartaoEditar = new ArrayList<IStrategy>();

		rnsClienteCartaoEditar.add(new ValidaEstoque());

		// mapa de Operacoes Cliente, RNs da operacao de ClienteCartao.
		Map<String, List<IStrategy>> mapaClienteCartao = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de ClienteCartao
		mapaClienteCartao.put("SALVAR", rnsEstoqueSalvar);
		mapaClienteCartao.put("EDITAR", rnsEstoqueEditar);
	// --------------------- Hash Documento ------------------------------//

		// Criando lista de RNs do Documento Salvar
		List<IStrategy> rnsDocumentoSalvar = new ArrayList<IStrategy>();

		rnsDocumentoSalvar.add(new PasseLivre());

		// Criando lista de RNs do Documento Alterar
		List<IStrategy> rnsDocumentoEditar = new ArrayList<IStrategy>();

		rnsDocumentoEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de Documento.
		Map<String, List<IStrategy>> mapaDocumento = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de Documento
		mapaDocumento.put("SALVAR", rnsDocumentoSalvar);
		mapaDocumento.put("EDITAR", rnsDocumentoEditar);
	// --------------------- Hash Endereco ------------------------------//

		// Criando lista de RNs do Endereco Salvar
		List<IStrategy> rnsEnderecoSalvar = new ArrayList<IStrategy>();

		rnsEnderecoSalvar.add(new PasseLivre());

		// Criando lista de RNs do Endereco Alterar
		List<IStrategy> rnsEnderecoEditar = new ArrayList<IStrategy>();

		rnsEnderecoEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de Endereco.
		Map<String, List<IStrategy>> mapaEndereco = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de Endereco
		mapaEndereco.put("SALVAR", rnsEnderecoSalvar);
		mapaEndereco.put("EDITAR", rnsEnderecoEditar);
	// --------------------- Hash CartaoCredito ------------------------------//

		// Criando lista de RNs do CartaoCredito Salvar
		List<IStrategy> rnsCartaoCreditoSalvar = new ArrayList<IStrategy>();

		rnsCartaoCreditoSalvar.add(new PasseLivre());

		// Criando lista de RNs do CartaoCredito Alterar
		List<IStrategy> rnsCartaoCreditoEditar = new ArrayList<IStrategy>();

		rnsCartaoCreditoEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de CartaoCredito.
		Map<String, List<IStrategy>> mapaCartaoCredito = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de CartaoCredito
		mapaCartaoCredito.put("SALVAR", rnsCartaoCreditoSalvar);
		mapaCartaoCredito.put("EDITAR", rnsCartaoCreditoEditar);
	// --------------------- Hash CartaoPedido ------------------------------//

		// Criando lista de RNs do CartaoPedido Salvar
		List<IStrategy> rnsCartaoPedidoSalvar = new ArrayList<IStrategy>();

		rnsCartaoPedidoSalvar.add(new PasseLivre());

		// Criando lista de RNs do CartaoPedido Alterar
		List<IStrategy> rnsCartaoPedidoEditar = new ArrayList<IStrategy>();

		rnsCartaoPedidoEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de CartaoPedido.
		Map<String, List<IStrategy>> mapaCartaoPedido = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de CartaoPedido
		mapaCartaoPedido.put("SALVAR", rnsCartaoPedidoSalvar);
		mapaCartaoPedido.put("EDITAR", rnsCartaoPedidoEditar);
	// --------------------- Hash Pedido ------------------------------//

		// Criando lista de RNs do Pedido Salvar
		List<IStrategy> rnsPedidoSalvar = new ArrayList<IStrategy>();

		rnsPedidoSalvar.add(new PasseLivre());

		// Criando lista de RNs do Pedido Alterar
		List<IStrategy> rnsPedidoEditar = new ArrayList<IStrategy>();

		rnsPedidoEditar.add(new PasseLivre());

		// mapa de Operacoes Cliente, RNs da operacao de Pedido.
		Map<String, List<IStrategy>> mapaPedido = new HashMap<String, List<IStrategy>>();

		// Adiconando lista de RNs para cada operacao de Pedido
		mapaPedido.put("SALVAR", rnsPedidoSalvar);
		mapaPedido.put("EDITAR", rnsPedidoEditar);
	// --------------------- Hash CartaPedido ------------------------------//

			// Criando lista de RNs do CartaPedido Salvar
			List<IStrategy> rnsCartaPedidoSalvar = new ArrayList<IStrategy>();

			rnsCartaPedidoSalvar.add(new PasseLivre());

			// Criando lista de RNs do CartaPedido Alterar
			List<IStrategy> rnsCartaPedidoEditar = new ArrayList<IStrategy>();

			rnsCartaPedidoEditar.add(new PasseLivre());

			// mapa de Operacoes Cliente, RNs da operacao de CartaPedido.
			Map<String, List<IStrategy>> mapaCartaPedido = new HashMap<String, List<IStrategy>>();

			// Adiconando lista de RNs para cada operacao de CartaPedido
			mapaCartaPedido.put("SALVAR", rnsCartaPedidoSalvar);
			mapaCartaPedido.put("EDITAR", rnsCartaPedidoEditar);
			
			

		regrasNegocio.put(Cliente.class.getName(), mapaCliente);
		regrasNegocio.put(ClienteDocumento.class.getName(), mapaClienteDocumento);
		regrasNegocio.put(ClienteEndereco.class.getName(), mapaClienteDocumento);
		regrasNegocio.put(ClienteCartao.class.getName(), mapaClienteCartao);
		regrasNegocio.put(Documento.class.getName(), mapaDocumento);
		regrasNegocio.put(Endereco.class.getName(), mapaEndereco);
		regrasNegocio.put(CartaoCredito.class.getName(), mapaCartaoCredito);
		regrasNegocio.put(CartaoPedido.class.getName(), mapaCartaoPedido);
		regrasNegocio.put(Cupom.class.getName(), mapaCupom);
		regrasNegocio.put(Estoque.class.getName(), mapaEstoque);
		regrasNegocio.put(Pedido.class.getName(), mapaPedido);
		regrasNegocio.put(Carta.class.getName(), mapaCarta);
		regrasNegocio.put(CartaPedido.class.getName(), mapaCartaPedido);

	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {

		resultado = new Resultado();
		sb.setLength(0);
		String nmClasse = entidade.getClass().getName();

		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
		List<IStrategy> rnsEntidade = mapaEntidade.get("SALVAR");

		executarRegras(entidade, rnsEntidade);

		if (sb.length() == 0) {

			IDao dao = daos.get(nmClasse);
			resultado = dao.salvar(entidade);

		} else {
			resultado.add(entidade);
			resultado.setMensagem((sb.toString()));
		}
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio entidade) {

		resultado = new Resultado();
		sb.setLength(0);
		String nmClasse = entidade.getClass().getName();

		Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
		List<IStrategy> rnsEntidade = mapaEntidade.get("EDITAR");

		executarRegras(entidade, rnsEntidade);

		if (sb.length() == 0) {
			IDao dao = daos.get(nmClasse);
			dao.editar(entidade);
			resultado.add(entidade);
		} else {
			resultado.add(entidade);
			resultado.setMensagem((sb.toString()));
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
//    	System.out.println("- Fachada Excluir");
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		IDao dao = daos.get(nmClasse);
		resultado.add(entidade);
		dao.excluir(entidade);

		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio entidade) {

		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		IDao dao = daos.get(nmClasse);
		resultado = dao.listar(entidade);

		return resultado;
	}

	private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
		for (IStrategy rn : rnsEntidade) {
			String msg = rn.processar(entidade);
			if (msg != null) {
				sb.append(msg);
			}
		}
	}

}
