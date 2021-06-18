package br.com.newstation.beans;

import javax.enterprise.inject.Model;
import javax.transaction.Transactional;

import br.com.newstation.command.ExcluirCommand;
import br.com.newstation.dominio.Cupom;

@Model
public class CupomExcluirBean {

	private Cupom cupom = new Cupom();

	@Transactional
	public String excluir(Cupom cup) {
		ExcluirCommand cmd = new ExcluirCommand();
		cmd.executar(cup);

		return "/admin/cupom/lista?faces-redirect=true";
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

}
