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
		if (total.doubleValue() > 10 & valorCartaoUm.doubleValue() < 10) {
			return true;
		}
		return false;
	}

	public boolean total(List<Cupom> cupons, BigDecimal total, Cupom somaDesc) {
		int soma_troca = 0;
		int soma_desc = 0;
		try  {
			soma_desc = (int) somaDesc.getPreco().doubleValue();
		}
		catch (Exception e) {
			soma_desc =0;
		}
		try {
			for (Cupom c : cupons) {
				soma_troca += c.getPreco().doubleValue();
			}
		} catch (Exception e) {
			soma_troca = 0;
		}

		if ((soma_desc + soma_troca) > (total.doubleValue() * 1.2)) {
			String alert = "Os Valores dos cupons ultrapassam  o valor máximo de " + (total.doubleValue() * 1.2)
					+ " remova algum cupom";
			return true;
		}
		return false;
	}

}
