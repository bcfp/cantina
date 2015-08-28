package ui;

import interfaces.ITelaConsultar;
import interfaces.ITelaDetalhar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.ProdutoVO;
import vo.UnidadeProdutoVO;

public class DetalharProdutoView extends JDialog implements ITelaDetalhar<ProdutoVO>, ITelaConsultar<UnidadeProdutoVO> {

	@Override
	public void abrirJanela(ProdutoVO produto) {
		
		JOptionPane.showMessageDialog(null, "Detalhar Produto");

	}

	@Override
	public void alterar() {

		
		
	}

	@Override
	public void deletar(UnidadeProdutoVO unidade) {
		
		
		
	}

	

}
