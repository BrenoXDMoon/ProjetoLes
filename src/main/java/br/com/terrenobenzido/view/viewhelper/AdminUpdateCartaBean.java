package br.com.terrenobenzido.view.viewhelper;

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
public class AdminUpdateCartaBean {

	@Inject
	private CartaDAO dao;
	@Inject
	private EstoqueDAO daoE;

	private Carta carta = new Carta();
	private Estoque estoque = new Estoque();
	private Integer id;
	private Part imagemCarta;

	@Transactional
	public void salvar() {
		carta.setEstoque(daoE.update(estoque));
		salvar_carta();
	}

	@Transactional
	public String salvar_carta() {

		if (imagemCarta != null) {
			FileSaver fileSaver = new FileSaver();
			carta.setImagemPath(fileSaver.write(imagemCarta, "cartas"));
		}

		dao.update(carta);

		return "/cartas/lista?faces-redirect=true";
	}

	public void carregaDetalhe() {
		this.setCarta(dao.buscarPorId(getId()));
		this.estoque = carta.getEstoque();
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Part getImagemCarta() {
		return imagemCarta;
	}

	public void setImagemCarta(Part imagemCarta) {
		this.imagemCarta = imagemCarta;
	}

}
