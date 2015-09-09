package ui;

import javax.swing.JOptionPane;

import interfaces.ITelaConsultar;
import vo.ProdutoVendaVO;
import vo.VendaVO;

public class ManterVendaView extends ManterDialogView<VendaVO> implements ITelaConsultar<ProdutoVendaVO> {

	protected ManterVendaView(String tituloCabecalho) {
		super(tituloCabecalho);
	}

	@Override
	public void abrirJanela(VendaVO objeto, ITelaConsultar<VendaVO> tela) {
		
		this.setVisible(true);
	
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public void deletar(ProdutoVendaVO objeto) {

		
		
	}

	@Override
	public boolean cadastrar(VendaVO item) {
		
		return false;
	}

	@Override
	protected boolean gravar() {
		
		return false;
	}

	@Override
	protected boolean alterar() {

		JOptionPane.showMessageDialog(null, "Venda Alterada");
		return false;
	
	}

	@Override
	protected void limparCampos() {
		
		
	}

}
