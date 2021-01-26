package br.com.terrenobenzido.view.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.terrenobenzido.infra.FileSaver;
import br.com.terrenobenzido.modelo.dao.CartaDAO;
import br.com.terrenobenzido.modelo.dao.EstoqueDAO;
import br.com.terrenobenzido.modelo.dominio.Carta;
import br.com.terrenobenzido.modelo.dominio.Estoque;

@Model
public class AdminCartaBean {

	@Inject
	private CartaDAO dao;

	@Inject
	private EstoqueDAO daoE;

	private Carta carta = new Carta();
	private Estoque estoque = new Estoque();
	private Part imagemCarta;
	private Integer quantidade;
	private Integer id;

	private List<Carta> cartas = new ArrayList<>();

	@Transactional
	public void salvar() {
		daoE.salvar(estoque);
		salvar_carta();
	}

	@Transactional
	public String salvar_carta() {

		carta.setAtivo(true);
		carta.setEstoque(estoque);
		dao.salvar(carta);

		FileSaver fileSaver = new FileSaver();
		carta.setImagemPath(fileSaver.write(imagemCarta, "cartas"));

		return "/cartas/lista?faces-redirect=true";
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
}
