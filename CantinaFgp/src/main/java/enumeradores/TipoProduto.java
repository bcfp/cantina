package enumeradores;

public enum TipoProduto {

	REVENDA("RV"), PRODUCAO("PR"), MATERIA_PRIMA("MP");
	
	private String tipoProduto;
	
	TipoProduto(){
		
	}

	TipoProduto(String tipoProduto){
		this.tipoProduto = tipoProduto;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}
	
}
