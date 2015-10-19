package vo;

import java.util.List;

import enumeradores.TipoProduto;

public class EstoqueProdutoVO extends GenericVO{
	
	private Long idEstoque;
	private String codEstoque;
	private ProdutoVO produto;
	private CantinaVO cantina;
	private Double qtdeMinima;
	private Double qtdeMaxima;
	private Double qtdeAtual;
	private List<LoteVO> lotes;
	
	public EstoqueProdutoVO() {
	
	}

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public CantinaVO getCantina() {
		return cantina;
	}

	public void setCantina(CantinaVO cantina) {
		this.cantina = cantina;
	}

	public Double getQtdeMinima() {
		return qtdeMinima;
	}

	public void setQtdeMinima(Double qtdeMinima) {
		this.qtdeMinima = qtdeMinima;
	}

	public Double getQtdeMaxima() {
		return qtdeMaxima;
	}

	public void setQtdeMaxima(Double qtdeMaxima) {
		this.qtdeMaxima = qtdeMaxima;
	}

	public Double getQtdeAtual() {
		return qtdeAtual;
	}

	public void setQtdeAtual(Double qtdeAtual) {
		this.qtdeAtual = qtdeAtual;
	}

	public List<LoteVO> getLotes() {
		return lotes;
	}

	public void setLotes(List<LoteVO> lotes) {
		this.lotes = lotes;
	}

	public String getCodEstoque() {
		return codEstoque;
	}

	public void setCodEstoque(String codEstoque) {
		this.codEstoque = codEstoque;
	}	
}
