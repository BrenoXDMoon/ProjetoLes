package br.com.newstation.fachadagenerator;

import java.util.HashMap;
import java.util.Map;

import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.ClienteCartao;
import br.com.newstation.dominio.ClienteDocumento;
import br.com.newstation.dominio.ClienteEndereco;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Pedido;
import br.com.newstation.fachada.FachadaCarta;
import br.com.newstation.fachada.FachadaCartaoCredito;
import br.com.newstation.fachada.FachadaCliente;
import br.com.newstation.fachada.FachadaCupom;
import br.com.newstation.fachada.FachadaDocumento;
import br.com.newstation.fachada.FachadaEndereco;
import br.com.newstation.fachada.FachadaPedido;
import br.com.newstation.fachada.IFachada;

public class GeradorFachada {

	Map<String, IFachada> fachadas = new HashMap<String, IFachada>();
	
	public GeradorFachada() {
		fachadas.put(Carta.class.getName(), new FachadaCarta());
		fachadas.put(Cliente.class.getName(), new FachadaCliente());
		fachadas.put(ClienteCartao.class.getName(), new FachadaCartaoCredito());
		fachadas.put(ClienteDocumento.class.getName(), new FachadaDocumento());
		fachadas.put(ClienteEndereco.class.getName(), new FachadaEndereco());
		fachadas.put(Cupom.class.getName(), new FachadaCupom());
		fachadas.put(Pedido.class.getName(), new FachadaPedido());
	}
	public IFachada retornaFachada(EntidadeDominio ent) {
		
		return fachadas.get(ent.getClass().getName());
	}
	
}
