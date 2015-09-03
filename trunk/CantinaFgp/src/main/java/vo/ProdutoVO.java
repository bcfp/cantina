package vo;

import java.util.List;

public abstract class ProdutoVO extends GenericVO {
	
	private Long idProduto;
	private String codProduto;
	private String descricao;
	private Double precoCusto;
	private UnidadeProdutoVO unidade;
	private Boolean status;
	private Boolean lote;
	private List<EstoqueProdutoVO> estoques;
	private List<ItemCompraVO> itemCompras;
	
	public ProdutoVO() {
	
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public Boolean getLote() {
		return lote;
	}

	public void setLote(Boolean lote) {
		this.lote = lote;
	}

	public List<EstoqueProdutoVO> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<EstoqueProdutoVO> estoques) {
		this.estoques = estoques;
	}

	public UnidadeProdutoVO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeProdutoVO unidade) {
		this.unidade = unidade;
	}

	public List<ItemCompraVO> getItemCompras() {
		return itemCompras;
	}

	public void setItemCompras(List<ItemCompraVO> itemCompras) {
		this.itemCompras = itemCompras;
	}
	
}
