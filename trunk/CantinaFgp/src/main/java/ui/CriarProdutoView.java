package ui;

import interfaces.ITelaCriar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.ProdutoVO;

public class CriarProdutoView extends CriarDialogView<ProdutoVO> implements ITelaCriar<ProdutoVO> {

	public CriarProdutoView() {
		super("Novo Produto");
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
		
		this.setVisible(true);

	}

	@Override
	protected void limparCampos() {
		
	}


}
