package daoimpl;

import java.util.List;

import vo.MateriaPrimaVO;
import daoservice.IMateriaPrimaDAO;

public class MateriaPrimaDAO implements IMateriaPrimaDAO {

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

	@Override
	public List<MateriaPrimaVO> consultarPorNomeECodigo(String nome, String cod) {
		return null;
	}

	

}
