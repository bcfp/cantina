package bo;

import java.util.Date;
import java.util.List;

import utils.UtilFuncoes;
import vo.CompraVO;
import vo.StatusVO;
import daoimpl.CompraDAO;
import daoservice.ICompraDAO;
import exceptions.AlteracaoCompraException;

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
	
	public boolean isValorValido(String campo){
		
		return UtilFuncoes.isValorNumerico(campo) && Double.parseDouble(campo) >= 0;
		
	}
	
	public boolean isQtdeValida(String campo){
		
		return UtilFuncoes.isValorNumerico(campo) && Double.parseDouble(campo) > 0;
		
	}

	// CRUD

	public CompraVO incluir(CompraVO compra) {

		return compraDao.incluir(compra);

	}

	public List<CompraVO> consultar() {

		return compraDao.consultar();

	}

	public boolean alterar(CompraVO compra) throws AlteracaoCompraException {
		
		if(!compra.getStatus().getDescricao().equals("Em Aberto")){
			
			throw new AlteracaoCompraException();
			
		}
		else{
			return compraDao.alterar(compra);
		}

	}

	public boolean deletar(CompraVO compra) {

		CompraVO c = compra;

		return compraDao.deletar(c.getIdCompra());

	}

}
