package ui;

import interfaces.ITelaConsultar;
import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.CadernetaVO;
import vo.ClienteVO;

public class DetalharClienteView extends JDialog implements ITelaDetalhar<ClienteVO>, ITelaConsultar<CadernetaVO> {

	@Override
	public void abrirJanela(ClienteVO venda) {
		
		JOptionPane.showMessageDialog(null, "Detalhar Cliente");

	}

	@Override
	public void alterar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(CadernetaVO objeto) {
		// TODO Auto-generated method stub
		
	}

}
