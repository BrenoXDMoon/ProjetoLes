package br.com.newstation.beans;

import java.text.ParseException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.newstation.command.EditarCommand;
import br.com.newstation.daos.CartaDao;
import br.com.newstation.daos.EstoqueDao;
import br.com.newstation.dominio.Carta;
import br.com.newstation.dominio.Estoque;
import br.com.newstation.dominio.RARIDADE;
import br.com.newstation.infra.FileSaver;

@Model
public class CartaEditarBean {

	@Inject
	private CartaDao dao;

	private EstoqueDao daoE = new EstoqueDao();

	private Carta carta = new Carta();
	private Estoque estoque = new Estoque();
	private Integer id;
	private Part imagemCarta;
	EditarCommand cmd = new EditarCommand();

	@Transactional
	public String salvar() throws ParseException {
		carta.setEstoque(daoE.update(estoque));
		
		if (imagemCarta != null) {
			FileSaver fileSaver = new FileSaver();
			carta.setImagemPath(fileSaver.write(imagemCarta, "cartas"));
		}

		carta.setAtivo(true);
		
		System.out.println("Carta estoque "+carta.getEstoque());

		cmd.executar(carta);

		return "/admin/cartas/lista?faces-redirect=true";
	}

	public void ativaCarta(Integer id) {
		Carta cardRef = dao.buscarPorId(id);
		cardRef.setAtivo(true);
		cmd.executar(cardRef);
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

	public RARIDADE[] getRaridade() {
		return RARIDADE.values();
	}

}
