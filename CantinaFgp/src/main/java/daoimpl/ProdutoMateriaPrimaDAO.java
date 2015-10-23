package daoimpl;

import java.util.ArrayList;
import java.util.List;

import enumeradores.TipoProduto;
import vo.EstoqueMateriaPrimaVO;
import vo.MateriaPrimaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVendaVO;

public class ProdutoMateriaPrimaDAO {
	
	public List<ProdutoMateriaPrimaVO> consultarReceitaPorIdProduto(Long idProduto){
		
		// Uma lista de ProdutoMateriaPrimaVO se torna uma receita
		List<ProdutoMateriaPrimaVO> receita = new ArrayList<ProdutoMateriaPrimaVO>();
		
		MateriaPrimaVO materiaPrima = new MateriaPrimaVO();
		materiaPrima.setCodProduto("1");
		materiaPrima.setDescricao("Farinha");
		
		// Um ProdutoMateriaPrimaVO é a relação entre um produto produzido e uma matéria prima dele
		ProdutoMateriaPrimaVO produtoMateriaPrima = new ProdutoMateriaPrimaVO();
		produtoMateriaPrima.setQtde(20d);
		produtoMateriaPrima.setMateriaPrima(materiaPrima);
		
		// Adiciona um produtoMateriaPrima na receita
		receita.add(produtoMateriaPrima);
		
		return receita;
		
	}

}
