package ui;

import interfaces.ITelaCriar;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vo.OrdemProducaoVO;

public class CriarOrdemProducao extends CriarDialogView<OrdemProducaoVO> implements ITelaCriar<OrdemProducaoVO>{

	private JTextField txtCodOp;
	private JTextField txtCodProdVenda;
	private JTextField txtProdVenda;
	private JTextField txtQtdeProdVenda;
	
	public CriarOrdemProducao() {
		super("Nova Ordem de Produção");
		abrirJanela();
	}

	@Override
	public void abrirJanela() {

		definicoesPagina();
		
	}

	@Override
	public boolean criarItem(OrdemProducaoVO ordemProducao) {
		
		return false;
		
	}

	@Override
	protected boolean gravar() {
		
		JOptionPane.showMessageDialog(null, "Ordem de Produção Salva");
		
		return true;
		
	}
	
	private void definicoesPagina() {
		
		txtCodOp = new JTextField();
		txtCodOp.setBounds(10, 10, 20, 20);
		
		getPanelCentro().add(txtCodOp);

		this.setVisible(true);
		
	}

	@Override
	protected void limparCampos() {
		
	}
	
	

}
