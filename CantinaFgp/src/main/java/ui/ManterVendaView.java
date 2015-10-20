package ui;

import interfaces.ITelaConsultar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import vo.ProdutoVendaVO;
import vo.StatusVO;
import vo.VendaVO;
import enumeradores.TipoSolicitacao;

public class ManterVendaView extends ManterDialogView<VendaVO> {

	// Atributos Tela
	
	private JComboBox<String> cbxStatus;
	
	private JTextField txtCodVenda;
	private JTextField txtCodProdVenda;
	private JTextField txtProdVenda;
	private JTextField txtQtdeProdVenda;
	private JTextField txtValorProdVenda;
	private JTextField txtCodCliente;
	
	private JLabel lblCliente;
	
	private JXDatePicker dtpDataVenda;
	private JComboBox<String> cbxFormaPgto;
	
	
	// Construtores
	
	protected ManterVendaView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	
	// Métodos Concretos
	
	@Override
	public void abrirJanela(VendaVO venda) {
		
		this.setVisible(true);
	
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Venda Incluída");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Venda Alterada");	
		return true;
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

}
