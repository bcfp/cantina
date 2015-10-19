package daoimpl;

import java.util.ArrayList;
import java.util.List;

import vo.EstoqueMateriaPrimaVO;
import vo.MateriaPrimaVO;
import vo.ReceitaVO;

public class ReceitaDAO {
	
	public ReceitaVO consultarPorIdProduto(Long idProduto){
		
		
		
		List<MateriaPrimaVO> listaMateriasPrima = new ArrayList<MateriaPrimaVO>();
		
		MateriaPrimaVO materiaPrima = new MateriaPrimaVO();
		materiaPrima.setCodProduto("1");
		materiaPrima.setDescricao("Farinha");
		
		ReceitaVO receita = new ReceitaVO();
		receita.setQtde(20d);
		receita.setMateriaPrima(materiaPrima);
		
		return receita;
	}

}
