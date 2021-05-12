package br.com.newstation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.RARIDADE;
import br.com.newstation.infra.FileSaver;
import br.com.newstation.infra.Log;

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

	private List<Carta> cartas = new ArrayList<>();

	@Transactional
	public String salvar() {
		daoE.salvar(estoque);
		carta.setAtivo(true);
		carta.setEstoque(estoque);
		
		Log.salvar("Inserção","admin");
		dao.salvar(carta);

		FileSaver fileSaver = new FileSaver();
		carta.setImagemPath(fileSaver.write(imagemCarta, "cartas"));

		return "/admin/cartas/lista?faces-redirect=true";
	}

	@Transactional
	public List<Carta> listar() {
		this.cartas = dao.listar();

		return cartas;
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

}
