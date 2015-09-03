package vo;

import enumeradores.TipoProduto;

public class ItemCompraVO extends GenericVO {

	private CompraVO compra;
	private ProdutoVO produto;
	private TipoProduto tipoProd;
	private Double qtde;
	private Double valor;
	
	public ItemCompraVO() {

	}

	public CompraVO getCompra() {
		return compra;
	}

	public void setCompra(CompraVO compra) {
		this.compra = compra;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public TipoProduto getTipoProd() {
		return tipoProd;
	}

	public void setTipoProd(TipoProduto tipoProd) {
		this.tipoProd = tipoProd;
	}
	
}
