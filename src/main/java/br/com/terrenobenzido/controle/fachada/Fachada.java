package br.com.terrenobenzido.controle.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.terrenobenzido.controle.strategy.IStrategy;
import br.com.terrenobenzido.controle.strategy.cartao.ValidaCartao;
import br.com.terrenobenzido.controle.strategy.cliente.CriptografaSenha;
import br.com.terrenobenzido.controle.strategy.cliente.ValidaCPF;
import br.com.terrenobenzido.controle.strategy.cliente.ValidaEmailExistente;
import br.com.terrenobenzido.controle.strategy.cliente.ValidaTrocaSenha;
import br.com.terrenobenzido.controle.strategy.endereco.ValidaEndereco;
import br.com.terrenobenzido.modelo.dao.CartaoCreditoDAO;
import br.com.terrenobenzido.modelo.dao.ClienteDAO;
import br.com.terrenobenzido.modelo.dao.EnderecoDAO;
import br.com.terrenobenzido.modelo.dao.IDAO;
import br.com.terrenobenzido.modelo.dominio.CartaoCredito;
import br.com.terrenobenzido.modelo.dominio.Cliente;
import br.com.terrenobenzido.modelo.dominio.Endereco;
import br.com.terrenobenzido.modelo.dominio.EntidadeDominio;
import br.com.terrenobenzido.modelo.dominio.Resultado;
import br.com.terrenobenzido.modelo.dominio.Senha;

public class Fachada implements IFachada {
	
	private Map<String, IDAO> daos;

    // Mapa Macro, com TODAS as regras de negocio
    // Observe: ele eh um mapa, de um mapa. E o mapa "menor" tem a lista de strategys
    private Map<String, Map<String, List<IStrategy>>> regrasNegocio;
    
    private StringBuilder sb = new StringBuilder();
    private Resultado resultado;

    public Fachada() {
        // instanciando mapas de daos e regras de negocio macro
        daos = new HashMap<String, IDAO>();
        // Instanciando o mapa macro;
        regrasNegocio = new HashMap<String, Map<String, List<IStrategy>>>();

        // adicionando todas os daos ao hash de daos
        daos.put(Cliente.class.getName(), new ClienteDAO());
        daos.put(Senha.class.getName(), new ClienteDAO());
        daos.put(Endereco.class.getName(), new EnderecoDAO());
        daos.put(CartaoCredito.class.getName(), new CartaoCreditoDAO());       

        // --------------------- Hash Cliente ------------------------------//
        // Criando RNs de cliente
        ValidaCPF validaCpf = new ValidaCPF();
        CriptografaSenha criptografaSenha = new CriptografaSenha();
        ValidaEmailExistente validaEmailExistente = new ValidaEmailExistente();
        
        // Criando lista de RNs do Cliente Salvar
        List<IStrategy> rnsClienteSalvar = new ArrayList<IStrategy>();
        rnsClienteSalvar.add(validaCpf);
        rnsClienteSalvar.add(criptografaSenha);
        rnsClienteSalvar.add(validaEmailExistente);
        
        // Criando lista de RNs do Cliente Alterar
        List<IStrategy> rnsClienteAlterar = new ArrayList<IStrategy>();
        rnsClienteAlterar.add(validaCpf);

        // Criando mapa de Operacoes Cliente, RNs da operacao de Cliente.
        Map<String, List<IStrategy>> mapaCliente = new HashMap<String, List<IStrategy>>();

        // Adiconando lista de RNs para cada operacao de cliente
        mapaCliente.put("SALVAR", rnsClienteSalvar);
        mapaCliente.put("ALTERAR", rnsClienteAlterar);

        regrasNegocio.put(Cliente.class.getName(), mapaCliente);

        // ------------------------ Hash Senha -------------------------------//
        // Criando RNs de senha
        List<IStrategy> rnsSenhaAlterar = new ArrayList<IStrategy>();
        ValidaTrocaSenha vTrocaSenha = new ValidaTrocaSenha();
        rnsSenhaAlterar.add(vTrocaSenha);

        // Criando mapa de Operacoes Senha, RNs da operacao de Senha.
        Map<String, List<IStrategy>> mapaSenha = new HashMap<String, List<IStrategy>>();

        // Adiconando lista de RNs para cada operacao de cliente
        mapaSenha.put("ALTERAR", rnsSenhaAlterar);
        regrasNegocio.put(Senha.class.getName(), mapaSenha);

        // --------------------------- Hash Endereco -------------------------//
        // Criando RNs de endereco
        ValidaEndereco vEndereco = new ValidaEndereco();

        // Criando lista de RNs do endereco Salvar
        List<IStrategy> rnsEnderecoSalvar = new ArrayList<IStrategy>();
        rnsEnderecoSalvar.add(vEndereco);

        // Criando lista de RNs do endereco Alterar
        List<IStrategy> rnsEnderecoAlterar = new ArrayList<IStrategy>();
        rnsEnderecoAlterar.add(vEndereco);

        // Criando mapa de Operacoes Endereco, RNs da operacao de Endereco.
        Map<String, List<IStrategy>> mapaEndereco = new HashMap<String, List<IStrategy>>();

        // Adiconando lista de RNs para cada operacao de Endereco
        mapaEndereco.put("SALVAR", rnsEnderecoSalvar);
        mapaEndereco.put("ALTERAR", rnsEnderecoAlterar);

        regrasNegocio.put(Endereco.class.getName(), mapaEndereco);

        // ------------------- Hash Cartao de Credito ------------------------//
        // Criando RNs de cartao
        ValidaCartao vCartao = new ValidaCartao();

        // Criando lista de RNs de cartao Salvar
        List<IStrategy> rnsCartaoCreditoSalvar = new ArrayList<IStrategy>();
        rnsCartaoCreditoSalvar.add(vCartao);

        // Criando lista de RNs de cartao Alterar
        List<IStrategy> rnsCartaoCreditoAlterar = new ArrayList<IStrategy>();
        rnsCartaoCreditoAlterar.add(vCartao);

        // Criando mapa de Operacoes CartaoCredito, RNs da operacao de CartaoCredito.
        Map<String, List<IStrategy>> mapaCartaoCredito = new HashMap<String, List<IStrategy>>();

        // Adiconando lista de RNs para cada operacao de Endereco
        mapaCartaoCredito.put("SALVAR", rnsCartaoCreditoSalvar);
        mapaCartaoCredito.put("ALTERAR", rnsCartaoCreditoAlterar);

        regrasNegocio.put(CartaoCredito.class.getName(), mapaCartaoCredito);        
        // ---------------------- Fim Regras de negocio ----------------------//
    }

    @Override
    public Resultado salvar(EntidadeDominio entidade) {
    	
    	System.out.println("- Fachada Salvar");
        resultado = new Resultado();
        sb.setLength(0);
        String nmClasse = entidade.getClass().getName();

        Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
        List<IStrategy> rnsEntidade = mapaEntidade.get("SALVAR");

        executarRegras(entidade, rnsEntidade);

        if (sb.length() == 0) {
            IDAO dao = daos.get(nmClasse);
            
            resultado = dao.salvar(entidade);
        } else {
            resultado.add(entidade);
            resultado.setMensagem(sb.toString());
        }
        return resultado;
    }

    @Override
    public Resultado editar(EntidadeDominio entidade) {
    	System.out.println("- Fachada Alterar");
        resultado = new Resultado();
        sb.setLength(0);
        String nmClasse = entidade.getClass().getName();

        Map<String, List<IStrategy>> mapaEntidade = regrasNegocio.get(nmClasse);
        List<IStrategy> rnsEntidade = mapaEntidade.get("ALTERAR");

        executarRegras(entidade, rnsEntidade);

        if (sb.length() == 0) {
            IDAO dao = daos.get(nmClasse);
            dao.editar(entidade);
            resultado.add(entidade);
        } else {
            resultado.add(entidade);
            resultado.setMensagem(sb.toString());
        }

        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {
    	System.out.println("- Fachada Excluir");
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        resultado.add(entidade);
        dao.excluir(entidade);

        return resultado;
    }

    @Override
    public Resultado listar(EntidadeDominio entidade) {
    	
    	System.out.println("- Fachada Consultar");
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        resultado.setEntidades(dao.listar(entidade).getEntidades());

        return resultado;

    }
    
    @Override
    public Resultado login(EntidadeDominio entidade) {
    	resultado = new Resultado();
    	Cliente cli = (Cliente) entidade;
    	
    	ClienteDAO dao = new ClienteDAO(); 
    	resultado.setEntidades(dao.login(entidade).getEntidades());
    	return resultado;
    }

    @Override
    public Resultado visualizar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        resultado.setEntidades(dao.listar(entidade).getEntidades());
        return resultado;
    }
    

    private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
//        for (IStrategy rn : rnsEntidade) {
//            String msg = rn.processar(entidade);
//            if (msg != null) {
//                sb.append(msg);
//            }
//        }
    }


}
