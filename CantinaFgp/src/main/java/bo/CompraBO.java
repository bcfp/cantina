package bo;

import java.util.Date;
import java.util.List;

import utils.UtilFuncoes;
import vo.CompraVO;
import daoimpl.CompraDAO;
import daoservice.ICompraDAO;

public class CompraBO {

	private ICompraDAO compraDao;

	{
		compraDao = new CompraDAO();
	}
	
	// Métodos
	
	public boolean isDataValida(Date data){
		
		if(new Date().before(data)){
			return false;
		}
		
		return true;
		
	}
	
	public boolean isCampoVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
		
	}

	// CRUD

	public CompraVO incluir(CompraVO compra) {

		return compraDao.incluir(compra);

	}

	public List<CompraVO> consultar() {

		return compraDao.consultar();

	}

	public boolean alterar(CompraVO compra) {

		return compraDao.alterar(compra);

	}

	public boolean deletar(CompraVO compra) {

		CompraVO c = compra;

		return compraDao.deletar(c.getIdCompra());

	}

}
