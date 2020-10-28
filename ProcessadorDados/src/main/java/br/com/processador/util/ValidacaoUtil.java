package br.com.processador.util;

public class ValidacaoUtil {

	public static boolean validaStringNulaVazia(String string) {
		return (string == null || string.isEmpty());
	}
	
	
	public static String montaMensagemErroCampoNaoInformado(String campoErro) {
		StringBuilder erro = new StringBuilder();
		erro.append("O campo ");
		erro.append(campoErro);
		erro.append(" não foi infomado");
		
		return erro.toString();
	}
}
