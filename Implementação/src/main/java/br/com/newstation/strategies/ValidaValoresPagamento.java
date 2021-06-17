package br.com.newstation.strategies;

import java.math.BigDecimal;
import java.util.List;

import br.com.newstation.dominio.Cupom;
import br.com.newstation.dominio.EntidadeDominio;

public class ValidaValoresPagamento implements IStrategy {

	@Override
	public String processar(EntidadeDominio ent) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean valorCataoUm(BigDecimal valorCartaoUm, BigDecimal valorCartaoDois, BigDecimal total) {
		if(total.doubleValue() > 10 & valorCartaoUm.doubleValue()<10) {
			return true;
		}
		return false;
	}

	public boolean total(List<Cupom> cupons, BigDecimal total, BigDecimal valorCartaoUm, Cupom soma_desc) {
		int soma_troca=0;
		for(Cupom c:cupons) {
			soma_troca += c.getPreco().doubleValue();
		}
		if((soma_desc.getPreco().doubleValue() + soma_troca) > (total.doubleValue() * 1.2)){
		  	String alert = "Os Valores dos cupons ultrapassam  o valor máximo de "+(total.doubleValue() * 1.2)+" remova algum cupom";
		  	return true;
		  }
		return false;
	}

}
