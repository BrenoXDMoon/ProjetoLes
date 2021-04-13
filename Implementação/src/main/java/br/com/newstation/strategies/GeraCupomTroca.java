package br.com.newstation.strategies;

import java.math.BigDecimal;
import java.util.UUID;

import javax.inject.Inject;

import br.com.newstation.daos.CupomDao;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.TIPO_CUPOM;

public class GeraCupomTroca {

	@Inject
	CupomDao dao;
	
	
	private static Cupom cupom = new Cupom();
	
	public static  Cupom gerarCupom(BigDecimal valor,Cliente cli) {
		cupom.setCodigo(UUID.randomUUID().toString().split("-")[0]);
		cupom.setPreco(valor);
		cupom.setTipoCupom(TIPO_CUPOM.Troca);
		cupom.setAtivo(true);
		cupom.setCliente(cli);
		return cupom;
	}
	
	public static Cupom getCupom() {
		return cupom;
	}

	public static void setCupom(Cupom cupom) {
		GeraCupomTroca.cupom = cupom;
	}

}
