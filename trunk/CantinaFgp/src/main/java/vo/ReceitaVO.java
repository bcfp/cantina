package vo;

public class ReceitaVO {
	
	private Long idReceita;
	private String codReceita;
	private ProdutoVendaVO produto;
	private MateriaPrimaVO materiaPrima;
	private Double qtde;
	private UnidadeProdutoVO unidade;
	
	public ReceitaVO() {

	}

	public Long getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(Long idReceita) {
		this.idReceita = idReceita;
	}

	public String getCodReceita() {
		return codReceita;
	}

	public void setCodReceita(String codReceita) {
		this.codReceita = codReceita;
	}

	public ProdutoVendaVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVendaVO produto) {
		this.produto = produto;
	}

	public MateriaPrimaVO getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrimaVO materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public UnidadeProdutoVO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeProdutoVO unidade) {
		this.unidade = unidade;
	}
		
}
