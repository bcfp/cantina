package vo;

import java.util.Date;
import java.util.List;

public class CompraVO extends GenericVO {

	private Long idCompra;
	private String codCompra;
	private Date data;
	private FornecedorVO fornecedor;
	private FuncionarioCantinaVO funcionario;
	private FormaPgtoVO formaPgto;
	private List<ItemCompraVO> itensCompra;
	private UnidadeProdutoVO unidade;
	
	public CompraVO() {

	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public String getCodCompra() {	
		return codCompra;
	}

	public void setCodCompra(String codCompra) {
		this.codCompra = codCompra;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public FornecedorVO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FormaPgtoVO getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgtoVO formaPgto) {
		this.formaPgto = formaPgto;
	}

	public List<ItemCompraVO> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompraVO> itensCompra) {
		this.itensCompra = itensCompra;
	}

	public UnidadeProdutoVO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeProdutoVO unidade) {
		this.unidade = unidade;
	}

	public FuncionarioCantinaVO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioCantinaVO funcionario) {
		this.funcionario = funcionario;
	}
	
}
