package vo;

import java.util.List;

public class ProdutoVendaVO extends ProdutoVO{

	private Double precoVenda;
	private Boolean fabricado;
	private Integer diasVencimento;
 	private List<OrdemProducaoVO> ordensProducao;
	
	public ProdutoVendaVO() {
	 
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<OrdemProducaoVO> getOrdensProducao() {
		return ordensProducao;
	}

	public void setOrdensProducao(List<OrdemProducaoVO> ordensProducao) {
		this.ordensProducao = ordensProducao;
	}

	public Boolean getFabricado() {
		return fabricado;
	}

	public void setFabricado(Boolean fabricado) {
		this.fabricado = fabricado;
	}

	public Integer getDiasVencimento() {
		return diasVencimento;
	}

	public void setDiasVencimento(Integer diasVencimento) {
		this.diasVencimento = diasVencimento;
	}

}
