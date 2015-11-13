package bo;

import vo.MateriaPrimaVO;
import daoimpl.MateriaPrimaDAO;
import daoservice.IMateriaPrimaDAO;

public class MateriaPrimaBO extends ProdutoBO<MateriaPrimaVO, IMateriaPrimaDAO> {

	private static IMateriaPrimaDAO matPrimaDao;
	
	static {
		matPrimaDao = new MateriaPrimaDAO();
	}
	
	public MateriaPrimaBO() {
		super(matPrimaDao);
	}

	
	
}
