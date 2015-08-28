package ui;

import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.OrdemProducaoVO;

public class DetalharOrdemProducaoView extends JDialog implements ITelaDetalhar<OrdemProducaoVO> {

	@Override
	public void abrirJanela(OrdemProducaoVO ordemProducao) {

		JOptionPane.showMessageDialog(null, "Detalhar OP");

	}

}
