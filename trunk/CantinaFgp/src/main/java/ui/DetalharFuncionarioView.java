package ui;

import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.FuncionarioVO;

public class DetalharFuncionarioView extends JDialog implements ITelaDetalhar<FuncionarioVO> {

	@Override
	public void abrirJanela(FuncionarioVO funcionario) {

		JOptionPane.showMessageDialog(null, "Detalhar Funcionario");
		
	}

}
