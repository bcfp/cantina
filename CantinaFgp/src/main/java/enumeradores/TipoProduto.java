package enumeradores;

public enum TipoProduto {

	REVENDA("Revenda"), PRODUCAO("Produção"), MATERIA_PRIMA("Matéria Prima");
	
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
