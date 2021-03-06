package br.com.newstation.seguranca;

import java.security.MessageDigest;

import org.jboss.security.Base64Encoder;

public class CriptografaSenha {

	public String criptoSenha(String senha) {

		try {
			byte[] digest = MessageDigest.getInstance("sha-256").digest(senha.getBytes());
			return Base64Encoder.encode(digest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
