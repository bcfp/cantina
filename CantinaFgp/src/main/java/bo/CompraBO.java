package bo;

import java.util.Date;
import java.util.List;

import utils.UtilFuncoes;
import vo.CompraVO;
import vo.FornecedorVO;
import vo.ItemCompraVO;
import vo.StatusVO;
import daoimpl.CompraDAO;
import daoimpl.StatusDAO;
import daoservice.ICompraDAO;
import enumeradores.TipoStatus;

public class CompraBO {

	private ICompraDAO compraDao;
	private StatusDAO statusDao;

	{
		compraDao = new CompraDAO();
		statusDao = new StatusDAO();
	}
	
	// Métodos
	
	public boolean isDataValida(Date data){
		
		if(new Date().before(data)){
			return false;
		}
		
		return true;
		
	}
	
	public boolean isAlteracaoPermitida(CompraVO compra){
		
		if(compra.getStatus().getDescricao().equals("Concluída")){
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
	
	public boolean isFornecedorValido(FornecedorVO fornecedor){
		
		return !(fornecedor == null || fornecedor.getIdFornecedor() == null || fornecedor.getIdFornecedor().equals(""));
		
	}
	
	public List<StatusVO> carregarStatusCompra(CompraVO compra){
		
		List<StatusVO> listaStatus = statusDao.consultar(TipoStatus.ORDEM_COMPRA);
		
		/*
		if(compra != null && compra.getStatus() != null){
			
			StatusVO statusCompra = compra.getStatus();
			
			if(statusCompra.getDescricao().equals("Aguardando Entrega")){
				
				for (int i = 0; i < listaStatus.size(); i++) {
					if(listaStatus.get(i).getDescricao().equals("Em Aberto")){
						listaStatus.remove(i);
					}
				}
					
			}
		}
		*/
		
		return listaStatus;
		
	}
	
	public boolean isListaItensCompraValida(List<ItemCompraVO> listaItensCompra){
		
		return !(listaItensCompra == null || listaItensCompra.size() == 0);
		
	}
	
	

	// CRUD

	public CompraVO incluir(CompraVO compra) {

		return compraDao.incluir(compra);

	}

	public boolean alterar(CompraVO compra) {
				
		return compraDao.alterar(compra);
		
	}

	public boolean deletar(CompraVO compra) {

		return compraDao.deletarCompra(compra);

	}

	public List<CompraVO> consultar() {

		return compraDao.consultar();

	}

}
