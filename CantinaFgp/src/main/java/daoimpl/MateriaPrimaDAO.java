package daoimpl;

import java.util.List;

import vo.MateriaPrimaVO;
import vo.ProdutoVendaVO;
import daoservice.IProdutoDAO;

public class MateriaPrimaDAO implements IProdutoDAO<MateriaPrimaVO> {

	@Override
	public boolean inserir(MateriaPrimaVO objeto) {
		
		return false;
	}

	@Override
	public boolean alterar(MateriaPrimaVO objeto) {
		
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		
		return false;
	}

	@Override
	public List<MateriaPrimaVO> consultarTodos() {
		
		return null;
	}

	@Override
	public MateriaPrimaVO consultarPorId(Long id) {
		
		return null;
	}

	

}
