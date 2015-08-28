package ui;

import interfaces.ITelaCriar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.ProdutoVO;

public class CriarProdutoView extends CriarDialogView<ProdutoVO> implements ITelaCriar<ProdutoVO> {

	public CriarProdutoView() {
		abrirJanela();
	}
	
	@Override
	public void abrirJanela() {
		
		definicoesPagina();
		
	}

	@Override
	public boolean criarItem(ProdutoVO produto) {
		
		return false;
		
	}
	
	@Override
	protected boolean gravar() {
		
		JOptionPane.showMessageDialog(null, "Produto Salvo");
		
		return true;
		
	}
	
	private void definicoesPagina() {

		this.setLayout(null);		
		
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 600);
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Novo Produto");
		this.setVisible(true);

	}


}
