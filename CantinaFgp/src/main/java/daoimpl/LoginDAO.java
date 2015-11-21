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

		System.out.println(usuario.getLogin().trim());
		System.out.println(usuario.getSenha().length());
		
		UsuarioVO usuarioLogado = null;
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select u.id_usuario, u.senha, u.login_usuario, u.ativo from usuario u "
					+ " where u.login_usuario = ? and u.senha = ? and u.ativo = 1");
			
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			
			rs = pstm.executeQuery();
						
			if(rs.next()){
				usuarioLogado = new UsuarioVO();
				usuarioLogado.setIdUsuario(rs.getLong("id_usuario"));
				usuarioLogado.setSenha(rs.getString("senha"));
				usuarioLogado.setLogin(rs.getString("login_usuario"));
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
