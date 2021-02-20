import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.newstation.dominio.Cidade;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.Endereco;
import br.com.newstation.dominio.Estado;
import br.com.newstation.dominio.TipoCliente;
import br.com.newstation.dominio.TipoDocumento;
import br.com.newstation.dominio.TipoEndereco;
import br.com.newstation.vh.ClienteBean;

class ClienteCadastroTeste {

	@Test
	void test() {
		Cliente cliente = new Cliente();
		
		List<Documento> documentos = new ArrayList<Documento>();
		
		documentos.add(criaDocumento("12345678912", 1, new Date(), "CPF", new Date()));
		documentos.add(criaDocumento("12345678912", 2, new Date(), "RG", new Date()));
		documentos.add(criaDocumento("12345678912", 3, new Date(), "CNH", new Date()));
		
		cliente.setDocumentos(documentos);
		
		cliente.setDtCadastro(new Date());
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		enderecos.add(criaEndereco("0000000", "Mogi das Cruzes", "São Paulo", "", 0, 1, "Rua do Pão", "1", "Residência"));
		enderecos.add(criaEndereco("0000000", "Mogi das Cruzes", "São Paulo", "", 0, 1, "Rua do Pão", "1", "Cobrança"));
		
		cliente.setEnderecos(enderecos);
		
		cliente.setId(1);
		
		cliente.setNome("Breno Gabriel Rodrigues da Silva");
		
		TipoCliente tp = new TipoCliente();
		
		tp.settipoCliente("Básico");
		
		cliente.setTipoCliente(tp);
		
		ClienteBean bean = new ClienteBean();
		
		bean.setCliente(cliente);
		
		bean.salvar();
	}
	
	Endereco criaEndereco(String cep, String cidade, String estado, String comp, Integer id, Integer idInt, String log, String numero, String tipoEnd) {
		Endereco end = new Endereco();
		
		end.setCep(cep);
		
		Cidade cid = new Cidade();
		
		cid.setCidade(cidade);
		Estado est = new Estado();
		est.setEstado(estado);
		cid.setEstado(est);
		
		end.setCidade(cid);
		
		end.setComplemento(comp);
		
		end.setDtCadastro(new Date());
		
		end.setId(id);
		
		end.setId(idInt);
		
		end.setLogradouro(log);
		
		end.setNumero(numero);
		
		TipoEndereco tp = new TipoEndereco();
		
		tp.setNome(tipoEnd);
		
		end.setTipoEndereco(tp);
		
		return end;
	}
	
	Documento criaDocumento(String cod, int id, Date data, String tipoDoc, Date validade) {
		
		Documento doc = new Documento();
		
		doc.setCodigo(cod);
		doc.setDtCadastro(new Date());
		doc.setId(1);
		TipoDocumento tp = new TipoDocumento();
		tp.setNome(tipoDoc);
		doc.setTipoDocumento(tp);
		doc.setValidade(validade);
		
		return doc;
	}

}
