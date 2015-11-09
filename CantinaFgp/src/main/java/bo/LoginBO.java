package bo;

import daoimpl.LoginDAO;
import daoservice.ILoginDAO;
import vo.UsuarioVO;

public class LoginBO {
	
	private ILoginDAO loginDao;
	
	{
		loginDao = new LoginDAO();
	}

	public boolean isUsuarioLogado(UsuarioVO usuario) {
		return loginDao.logarUsuario(usuario);
	}

}
