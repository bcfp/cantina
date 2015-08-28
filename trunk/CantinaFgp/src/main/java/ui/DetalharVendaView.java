package ui;

import interfaces.ITelaConsultar;
import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.ProdutoVendaVO;
import vo.VendaVO;

public class DetalharVendaView extends JDialog implements ITelaDetalhar<VendaVO>, ITelaConsultar<ProdutoVendaVO> {

	@Override
	public void abrirJanela(VendaVO venda) {

		JOptionPane.showMessageDialog(null, "Detalhar Venda");

	}

	@Override
	public void alterar() {

		
		
	}

	@Override
	public void deletar(ProdutoVendaVO objeto) {

		
		
	}

}
