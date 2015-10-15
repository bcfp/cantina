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
	public void abrirJanela(VendaVO objeto) {
		
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
	public boolean incluir(VendaVO venda) {
		
		return false;
	}

	@Override
	public boolean alterar(VendaVO venda) {
		
		return false;
	}

	@Override
	protected boolean btnIncluir() {
		
		return false;
	}

	@Override
	protected boolean btnAlterar() {
		
		return false;
	}

	@Override
	protected boolean habilitarCampos() {

		JOptionPane.showMessageDialog(null, "Venda Alterada");
		return false;
	
	}

	@Override
	protected void limparCampos() {
		
		
	}

}
