package ui;

import interfaces.ITelaConsultar;

import javax.swing.JOptionPane;

import vo.ProdutoVendaVO;
import vo.VendaVO;
import enumeradores.TipoManter;

public class ManterVendaView extends ManterDialogView<VendaVO> implements ITelaConsultar<ProdutoVendaVO> {

	protected ManterVendaView(TipoManter solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela(VendaVO venda) {
		
		this.setVisible(true);
	
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public void deletar(ProdutoVendaVO produtoVenda) {
		
	}

	@Override
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Venda Incluída");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Venda Alterada");	
		return false;
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

}
