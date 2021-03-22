package br.com.newstation.strategies;

import br.com.newstation.beans.ClienteSalvarBean;
import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.TIPO_DOCUMENTO;

public class ValidaCPF implements IStrategy {
	
	private static final int TAMANHO_CPF = 11;
	private static final int[] PESOS_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	@Override
	public String processar(EntidadeDominio ent) {

//		System.out.println("- entrou na poha");
		
//		ClienteSalvarBean csb = new ClienteSalvarBean();
		Cliente cliente = (Cliente) ent;

		Boolean isValid = true;
		Boolean isCPF = false;

		Documento doc = new Documento();
		for (Documento d : cliente.getDocumentos()) {
			if (d.getTipoDocumento().equals(TIPO_DOCUMENTO.CPF)) {
				isCPF = true;
				doc = d;
			}
		}
		
//		System.out.println(cpfValido(doc.getCodigo()));
		
		if(cpfValido(doc.getCodigo())) {
			return null;
		}else
		{
//			System.out.println("CPF INVÁLIDO");
			return "CPF INVÁLIDO";
		}
	}
	
	public boolean cpfValido(String cpf) {
        if (cpf == null) {
            return false;
        }
        if (cpf.length() != TAMANHO_CPF) {
            return false;
        }
        if (cpf.matches(cpf.charAt(0) + "{" + TAMANHO_CPF + "}")) {
            return false;
        }
        String digitos = cpf.substring(0, TAMANHO_CPF - 2);
        String verificador1 = verificador(digitos, PESOS_CPF);
        String verificador2 = verificador(digitos + verificador1, PESOS_CPF);
        return (digitos + verificador1 + verificador2).equals(cpf);
    }

    private String verificador(String digitos, int[] pesos) {
        int soma = 0;
        int qtdPesos = pesos.length;
        int qtdDigitos = digitos.length();
        for (int posicao = qtdDigitos - 1; posicao >= 0; posicao--) {
            int digito = Character.getNumericValue(digitos.charAt(posicao));
            soma += digito * pesos[qtdPesos - qtdDigitos + posicao];
        }
        soma = 11 - soma % 11;
        return String.valueOf(soma > 9 ? 0 : soma);
    }
}
