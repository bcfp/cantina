package ui;

import javax.swing.JOptionPane;

import vo.ProdutoVO;

public class CadastrarProdutoView extends CadastrarDialogView<ProdutoVO>{

	public CadastrarProdutoView() {
		super("Novo Produto");
		abrirJanela();
	}
	
	@Override
	public void abrirJanela() {

		this.setVisible(true);
		
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
	
	@Override
	protected void limparCampos() {
		
	}


}
