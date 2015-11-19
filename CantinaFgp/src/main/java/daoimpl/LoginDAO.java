package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.UsuarioVO;
import daoservice.ILoginDAO;

public class LoginDAO implements ILoginDAO {
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}

	@Override
	public UsuarioVO logarUsuario(UsuarioVO usuario) {
		
		UsuarioVO usuarioLogado = null;
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select u.id_usuario, u.senha, u.login_usuario, u.ativo from usuario "
					+ "where u.login like ? and u.senha like ?");
			
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				usuarioLogado = new UsuarioVO();
				usuarioLogado.setIdUsuario(rs.getLong("id_usuario"));
				usuarioLogado.setSenha(rs.getString("senha"));
				usuarioLogado.setLogin(rs.getString("login_usuario"));
				usuarioLogado.setAtivo(rs.getBoolean("ativo"));	
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
		
		return usuarioLogado;
	}

	
	
}
