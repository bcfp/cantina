package ui;

import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.CantinaVO;

public class DetalharCantinaView extends JDialog implements ITelaDetalhar<CantinaVO> {

	@Override
	public void abrirJanela(CantinaVO cantina) {

		JOptionPane.showMessageDialog(null, "Detalhar Cantina");

	}

}
