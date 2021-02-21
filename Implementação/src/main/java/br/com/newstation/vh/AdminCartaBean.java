package br.com.newstation.vh;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.infra.FileSaver;

@Model
public class AdminCartaBean {

	@Inject
	private EstoqueDao daoE;

	@Inject
	private FacesContext context;

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
		dao.salvar(carta);

		FileSaver fileSaver = new FileSaver();
		carta.setImagemPath(fileSaver.write(imagemCarta, "cartas"));

		context.getExternalContext().getFlash().setKeepMessages(true);

		context.addMessage(null, new FacesMessage("Carta cadastrada com sucesso!"));

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
