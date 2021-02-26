package br.com.newstation.strategies;

import java.util.InputMismatchException;

import br.com.newstation.dominio.Cliente;
import br.com.newstation.dominio.Documento;
import br.com.newstation.dominio.EntidadeDominio;
import br.com.newstation.dominio.TIPO_DOCUMENTO;

public class ValidaCPF implements IStrategy {

	@Override
	public String processar(EntidadeDominio ent) {
		
		Cliente cliente = (Cliente) ent;
		
        Boolean isValid = true;
        Boolean isCPF = false;
        
        Documento doc = new Documento();
        for (Documento d : cliente.getDocumentos()){
        	if(d.getTipoDocumento().equals(TIPO_DOCUMENTO.CPF)) {
        		isCPF = true;
        		doc = d;
        	}
        }
        
        if(isCPF.equals(true)) {
        	String CPF = doc.getCodigo();
            
            
            if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11)) {
                    isValid = false;
                    return "CPF inválido";
            }
                
              
            char dig10, dig11;
            int sm, i, r, num, peso;
              
            // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
            // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i=0; i<9; i++) {              
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere '0' no inteiro 0         
            // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int)(CPF.charAt(i) - 48); 
                sm = sm + (num * peso);
                peso = peso - 1;
                }
              
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
              
            // Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
                }
              
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                     dig11 = '0';
                else dig11 = (char)(r + 48);
              
            // Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                     isValid = true;
                else{ 
                    isValid = false;
                    return "CPF inválido";
                }
                    } catch (InputMismatchException erro) {
                    isValid = false;
                }
                if(!isValid)
                    return "CPF inválido";
                else
                    return null;
            }else {
            	
            	return null;
            	
            }
        }  
}
