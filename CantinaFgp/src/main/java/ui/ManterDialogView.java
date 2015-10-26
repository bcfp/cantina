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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.GenericVO;
import enumeradores.TipoSolicitacao;

/**
 * Tela de manter padrão. As demais telas de manter devem herdar esta classe.
 * As telas de manter são utilizadas para detalhar e alterar um item do tipo passado por Generic Type.
 * 
 * @author bruno.silva
 *
 * @param <T> - Deve ser passado como generic type um objeto do tipo GenericVO. Este objeto será utilizado para 
 * definir o tipo dos parâmetros dos métodos da tela de manter.
 * 
 */
public abstract class ManterDialogView<T extends GenericVO> extends JFrame implements ITelaManter<T> {

	
	// Atributos da Janela
	
	private JButton btnGravar;
	private JButton btnAlterar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JPanel pnlCentro;
	private JPanel pnlCabecalho;
	private JPanel pnlRodape;
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	
	
	// Construtores
	
	protected ManterDialogView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		
		criarPaneis(tituloCabecalho);
		
		if(solicitacao.equals(TipoSolicitacao.DETALHAR)){
			
			btnAlterar = new JButton("Alterar");
			
			btnAlterar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					btnAlterar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnGravar.setEnabled(true);
					habilitarCampos();
					
				}
			});
			
			pnlRodape.add(btnAlterar);

			btnLimpar.setEnabled(false);
			btnGravar.setEnabled(false);
			btnGravar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(ManterDialogView.this.alterar()){
						btnAlterar.setEnabled(true);
						btnLimpar.setEnabled(false);
						btnGravar.setEnabled(false);
					}
					
				}
			});
		}
		else{
			if(solicitacao.equals(TipoSolicitacao.INCLUIR)){
				btnGravar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						incluir();
						
					}
					
				});
			}
		}
	}
	
	
	// Métodos concretos
	
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
		this.setResizable(false);
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		
	}

	
	protected void adicionarComponentesCentro(JComponent comp){
		
		pnlCentro.add(comp);
		
	}
	
	// métodos abstratos
	
	protected abstract boolean habilitarCampos();
	protected abstract void limparCampos();
	
}
