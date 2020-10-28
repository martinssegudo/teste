package br.com.processador.exceptions;

public class ArquivoException extends Exception{
	
	private static final long serialVersionUID = -3877806799293057704L;
	public String menssagem;
	
	public ArquivoException(String menssagem, Throwable e) {
		super(menssagem, e.getCause());
		this.menssagem = menssagem;
	}
	
	public ArquivoException(String menssagem) {
		super(menssagem);
		this.menssagem = menssagem;
	}

}
