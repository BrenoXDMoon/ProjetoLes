package br.com.newstation.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.newstation.daos.ClienteDao;
import br.com.newstation.daos.IDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.strategies.IStrategy;
import br.com.newstation.strategies.ValidaCPF;
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

        regrasNegocio.put(Cliente.class.getName(), mapaCliente);

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
           // resultado = dao.salvar(entidade);
            
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
    	System.out.println("- Fachada Excluir");
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
