package enumeradores;

public enum TipoProduto {

	REVENDA("Revenda"), PRODUCAO("Produção"), MATERIA_PRIMA("Matéria Prima");
	
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
