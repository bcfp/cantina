package bo;

import java.util.ArrayList;
import java.util.List;

import vo.ProdutoVendaVO;
import daoimpl.ProdutoVendaDAO;
import daoservice.IProdutoVendaDAO;
import enumeradores.TipoProduto;

public class ProdutoVendaBO extends ProdutoBO<ProdutoVendaVO, IProdutoVendaDAO> {
	
	public static IProdutoVendaDAO produtoVendaDao;
	
	static{
		produtoVendaDao = new ProdutoVendaDAO();
	}

	public ProdutoVendaBO() {
		super(produtoVendaDao);
	}
	
	public List<ProdutoVendaVO> filtrarProdutoFabricadoPorCodigoENome(String nome, String cod){
		
		List<ProdutoVendaVO> produtosVenda = filtrarProdutoVendaPorCodigoENome(nome, cod);
		List<ProdutoVendaVO> produtosFabricados = new ArrayList<ProdutoVendaVO>();
		
		for (ProdutoVendaVO produtoVenda : produtosVenda) {
			
			if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
				
				produtosFabricados.add(produtoVenda);
				
			}
			
		}
		
		return produtosFabricados;
		
	}
	
	public List<ProdutoVendaVO> filtrarProdutoRevendaPorCodigoENome(String nome, String cod){
		
		List<ProdutoVendaVO> produtosVenda = filtrarProdutoVendaPorCodigoENome(nome, cod);
		List<ProdutoVendaVO> produtosRevenda = new ArrayList<ProdutoVendaVO>();
		
		for (ProdutoVendaVO produtoVenda : produtosVenda) {
			
			if(produtoVenda.getTipo().equals(TipoProduto.REVENDA)){
				
				produtosRevenda.add(produtoVenda);
				
			}
			
		}
		
		return produtosRevenda;
		
	}
	
	

	

}
