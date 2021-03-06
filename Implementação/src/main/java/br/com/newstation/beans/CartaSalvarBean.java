package br.com.newstation.beans;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.newstation.command.SalvarCommand;
import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.RARIDADE;
import br.com.newstation.dominio.Resultado;
import br.com.newstation.infra.FileSaver;

@Model
public class CartaSalvarBean {

	@Inject
	private EstoqueDao daoE;

	@Inject
	private CartaDao dao;

	private Carta carta = new Carta();
	private Estoque estoque = new Estoque();
	private Part imagemCarta;
	private Integer quantidade;
	private Integer id;
	private BigDecimal valorCusto;
	private String fornecedor;
	SalvarCommand cmd = new SalvarCommand();
	

	private List<Carta> cartas = new ArrayList<>();

	@Transactional
	public String salvar() throws ParseException {
		cmd.executar(estoque);
//		daoE.salvar(estoque);
		carta.setAtivo(true);
		carta.setEstoque(estoque);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    LocalDateTime now = LocalDateTime.now();  
	    System.out.println(dtf.format(now));
        
	    carta.setDataEntrada(dtf.format(now));
        

//		dao.salvar(carta);

		FileSaver fileSaver = new FileSaver();
		carta.setImagemPath(fileSaver.write(imagemCarta, "cartas"));

		
		Resultado resultado = cmd.executar(carta);
		this.carta = (Carta) resultado.getEntidade();
		
		return "/admin/cartas/lista?faces-redirect=true";
	}

	@Transactional
	public List<Carta> listar() {
		
		this.cartas = converteLista(dao.listar(this.carta));

		return cartas;
	}

	private List<Carta> converteLista(Resultado listar) {
		
		List<Carta> lista = new ArrayList<Carta>();
		
		for(EntidadeDominio e : listar.getEntidades()) {
			Carta c = (Carta) e;
			lista.add(c);
		}
		
		return lista;
	}

	public void carregaDetalhe() {
		this.carta = dao.buscarPorId(id);
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public Part getImagemCarta() {
		return imagemCarta;
	}

	public void setImagemCarta(Part imagemCarta) {
		this.imagemCarta = imagemCarta;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

}
