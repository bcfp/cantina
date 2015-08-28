package enumeradores;

public enum TipoProduto {

	REVENDA("RV"), PRODUCAO("PR"), MATERIA_PRIMA("MP");
	
	private String tipoProduto;
	
	TipoProduto(){
		
	}

	TipoProduto(String tipoProduto){
		this.setTipoProduto(tipoProduto);
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
}
