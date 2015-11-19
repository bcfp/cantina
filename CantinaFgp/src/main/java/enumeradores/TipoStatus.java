package enumeradores;

public enum TipoStatus {

	ORDEM_COMPRA("OC"), ORDEM_PRODUCAO("OP"), VENDA("OV");
	
	private String tipo;
	
	TipoStatus(){
		
	}
	
	TipoStatus(String tipo){
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
