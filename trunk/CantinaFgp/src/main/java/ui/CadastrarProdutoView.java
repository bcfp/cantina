package ui;

import interfaces.ITelaCadastrar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.ProdutoVO;

public class CadastrarProdutoView extends CadastrarDialogView<ProdutoVO>{

	public CadastrarProdutoView() {
		super("Novo Produto");
		abrirJanela();
	}
	
	@Override
	public void abrirJanela() {
		
		definicoesPagina();
		
	}

	@Override
	public boolean cadastrar(ProdutoVO produto) {
		
		return false;
		
	}
	
	@Override
	protected boolean botaoGravar() {
		
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
