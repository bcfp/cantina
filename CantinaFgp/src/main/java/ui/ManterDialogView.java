package ui;

import interfaces.ITelaManter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.GenericVO;
import enumeradores.TipoSolicitacao;

public abstract class ManterDialogView<T extends GenericVO> extends JDialog implements ITelaManter<T> {

	private JButton btnGravar;
	private JButton btnAlterar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JPanel pnlCentro;
	private JPanel pnlCabecalho;
	private JPanel pnlRodape;
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	
	protected ManterDialogView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		
		criarPaneis(tituloCabecalho);
		
		if(solicitacao.equals(TipoSolicitacao.DETALHAR)){
			incluirBotaoAlterar();
			btnGravar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ManterDialogView.this.alterar();
					
				}
				
			});
		}
		else{
			if(solicitacao.equals(TipoSolicitacao.INCLUIR)){
				btnGravar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						ManterDialogView.this.incluir();
						
					}
					
				});
			}
		}
	}
	
	private void criarPaneis(String tituloCabecalho){
		
		lblTituloCabecalho = new JLabel();
		lblTituloCabecalho.setText(tituloCabecalho);
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		
		pnlCabecalho = new JPanel();
		pnlCabecalho.add(lblTituloCabecalho);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setBackground(Color.BLACK);
		
		pnlCentro = new JPanel();
		pnlCentro.setBackground(Color.GRAY);
		pnlCentro.setLayout(null);
		
		pnlRodape = new JPanel();
		pnlRodape.setBackground(Color.WHITE);
		
		// BOTÕES
		
		btnGravar = new JButton("Gravar");

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ManterDialogView.this.limparCampos();
				
			}
			
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				ManterDialogView.this.dispose();
				
			}
		});

		pnlRodape.add(btnGravar);
		pnlRodape.add(btnLimpar);
		pnlRodape.add(btnCancelar);
			
		// Definições página
		
		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlRodape, BorderLayout.SOUTH);		
		this.add(pnlCentro, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(700, 600);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		
	}
	
	// métodos

	protected abstract boolean habilitarCampos();
	protected abstract void limparCampos();
	
	private void incluirBotaoAlterar(){
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ManterDialogView.this.habilitarCampos();
				
			}
		});
		pnlRodape.add(btnAlterar);
	}
	
	protected void incluirComponenteCentro(JComponent comp){
		
		pnlCentro.add(comp);
		
	}
	
}
