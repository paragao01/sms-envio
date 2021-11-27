package br.com.unipix.envio.exception;

public class BusinessException extends RuntimeException{
	static final long serialVersionUID = 1L;

	public BusinessException (String mensage) {
		super(mensage);
	}
	
	public BusinessException() {
		super();
	}
	
}
