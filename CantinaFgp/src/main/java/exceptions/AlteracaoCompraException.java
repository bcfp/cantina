package exceptions;

public class AlteracaoCompraException extends Exception {

	@Override
	public String getMessage(){
		
		return "Esta compra não pode ser alterada";
		
	}
	
}

