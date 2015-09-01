package ui;

import interfaces.ITelaCadastrar;

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

public abstract class CadastrarDialogView<T extends GenericVO> extends JDialog implements ITelaCadastrar<T> {

	private JButton btnGravar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JPanel pnlCentro;
	private JPanel pnlCabecalho;
	private JPanel pnlRodape;
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	
	protected CadastrarDialogView(String tituloCabecalho) {

		criarPaneis(tituloCabecalho);

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
		btnGravar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CadastrarDialogView.this.botaoGravar();
				
			}
			
		});
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CadastrarDialogView.this.limparCampos();
				
			}
			
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				CadastrarDialogView.this.dispose();
				
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
		this.setSize(800, 600);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		
	}
	
	protected abstract boolean botaoGravar();
	protected abstract void limparCampos();

	
	// método
	
	protected void incluirComponenteCentro(JComponent comp){
		
		pnlCentro.add(comp);
		
	}
	
}
