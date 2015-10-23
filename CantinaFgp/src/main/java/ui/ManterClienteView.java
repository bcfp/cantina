﻿package ui;

import javax.swing.JOptionPane;

import enumeradores.TipoSolicitacao;
import interfaces.ITelaConsultar;
import vo.ClienteVO;
import vo.GenericVO;

public class ManterClienteView extends ManterDialogView<ClienteVO> {


	protected ManterClienteView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela(ClienteVO objeto) {
	
		this.setVisible(true);
		
	}	
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Cliente incluído");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Cliente alterado");		
		return true;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}
	
}
