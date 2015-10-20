package daoimpl;

import java.util.ArrayList;
import java.util.List;

import vo.EstoqueMateriaPrimaVO;
import vo.MateriaPrimaVO;
import vo.ProdutoMateriaPrimaVO;

public class ReceitaDAO {
	
	public ProdutoMateriaPrimaVO consultarPorIdProduto(Long idProduto){
		
		
		
		List<MateriaPrimaVO> listaMateriasPrima = new ArrayList<MateriaPrimaVO>();
		
		MateriaPrimaVO materiaPrima = new MateriaPrimaVO();
		materiaPrima.setCodProduto("1");
		materiaPrima.setDescricao("Farinha");
		
		ProdutoMateriaPrimaVO receita = new ProdutoMateriaPrimaVO();
		receita.setQtde(20d);
		receita.setMateriaPrima(materiaPrima);
		
		return receita;
	}

}
