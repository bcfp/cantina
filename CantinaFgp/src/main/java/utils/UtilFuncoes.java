package utils;


public class UtilFuncoes {
	
	public static Boolean isVazio(String campo){
		  
		  return campo == null || campo.equals("");
		  
	}
	
	public static Integer doubleToInteger(Double valor){
		return valor.intValue();
	}
}
