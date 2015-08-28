package ui;

import interfaces.ITelaCriar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.OrdemProducaoVO;

public class CriarOrdemProducao extends CriarDialogView<OrdemProducaoVO> implements ITelaCriar<OrdemProducaoVO>{
	
	public CriarOrdemProducao() {
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

		this.setLayout(null);		
		
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 600);
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Nova Ordem de Produção");
		this.setVisible(true);

	}
	
	

}
