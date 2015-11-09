package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.BancoFake;
import vo.StatusVO;
import daoservice.IStatusDAO;
import enumeradores.TipoStatus;

public class StatusDAO implements IStatusDAO{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}

	@Override
	public boolean incluir(StatusVO objeto) {
		
		return false;
	}

	@Override
	public boolean alterar(StatusVO objeto) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Deprecated
	@Override
	public List<StatusVO> consultar() {
		return null;
	}

	@Override
	public StatusVO consultarPorId(Long id) {
		
		return null;
	}

	@Override
	public List<StatusVO> consultar(TipoStatus tipo) {
		
		List<StatusVO> listaStatus = null;
		
		if(tipo.equals(TipoStatus.ORDEM_PRODUCAO)){
			
			listaStatus = BancoFake.listaStatusOP;
			/*
			List<StatusVO> listaStatus = new ArrayList<StatusVO>();
			
			try {
				conexao = fabrica.getConexao();
				
				pstm = conexao.prepareStatement("select id_status, descricao, tipo from status");
				
				rs = pstm.executeQuery();
				
				StatusVO status = null;
				
				while(rs.next()){
					
					status = new StatusVO();
					status.setIdStatus(rs.getLong("id_status"));
					status.setDescricao(rs.getString("descricao"));
					if(rs.getString("tipo") == "OP"){
						status.setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
					}
					else if(rs.getString("tipo") == "OC"){
						status.setTipoStatus(TipoStatus.ORDEM_COMPRA);
					}
					else if(rs.getString("tipo") == "VD"){
						status.setTipoStatus(TipoStatus.VENDA);
					}
					else{
						status.setTipoStatus(TipoStatus.GENERICO);
					}
					
					listaStatus.add(status);
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					conexao.close();
					pstm.close();
					if(rs != null){
						
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			*/
		}
		else{
			if(tipo.equals(TipoStatus.ORDEM_COMPRA)){
				listaStatus = BancoFake.listaStatusOC;
			}
		}
		return listaStatus;		
		
	}
	
	

}
