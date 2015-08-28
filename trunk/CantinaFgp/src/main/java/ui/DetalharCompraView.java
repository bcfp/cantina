package ui;

import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.CompraVO;

public class DetalharCompraView extends JDialog implements ITelaDetalhar<CompraVO> {

	@Override
	public void abrirJanela(CompraVO compra) {

		JOptionPane.showMessageDialog(null, "Detalhar Compra");

	}

}
