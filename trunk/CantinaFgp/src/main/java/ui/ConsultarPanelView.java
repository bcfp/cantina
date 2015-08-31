package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.GenericVO;

public abstract class ConsultarPanelView<T extends GenericVO> extends JPanel{
	
	
	// atributos
	
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlRodape;

	private JTable tabGeneric;
	private DefaultTableModel modeloTabGeneric;
	private JScrollPane barraTabGeneric;

	private JLabel lbltituloCabecalho;
	private JButton btnFechar;
	private JButton btnNovo;
	
	
	
	// Construtor
	
	public ConsultarPanelView(String tituloCabecalho, String[] titulos, List<T> listaGenericos, int espX, int espY, int larg, int alt) {
		
		criarPainel(tituloCabecalho, titulos, listaGenericos, espX, espY, larg, alt);
		
	}
	
	// métodos concretos
	
	private void criarPainel(String tituloCabecalho, String[] titulos, List<T> listaGenerics, int espX, int espY, int larg, int alt){
		
		
		pnlCabecalho = new JPanel();
		pnlCentro = new JPanel();	
		pnlCentro.setLayout(null);
		pnlRodape = new JPanel();
		pnlRodape.setLayout(new FlowLayout());
		
		lbltituloCabecalho = new JLabel();
		lbltituloCabecalho.setText(tituloCabecalho);
		pnlCabecalho.add(lbltituloCabecalho);
		
		// tabela
		
		tabGeneric = new JTable();
		modeloTabGeneric = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		barraTabGeneric = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		modeloTabGeneric.setColumnIdentifiers(titulos);
		
		tabGeneric.setModel(modeloTabGeneric);
		
		barraTabGeneric.setViewportView(tabGeneric);
		barraTabGeneric.setBounds(espX, espY, larg, alt);

		pnlCentro.add(barraTabGeneric);
		
		carregarGridItens(listaGenerics);
		mouseClickedTab();
		
		
		// botões
		
		btnFechar = new JButton("Fechar Tela");
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.setVisible(false);
				
			}
			
		});
		
		btnFechar.setVisible(true);
		
		btnNovo = new JButton("Novo");
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.getTelaNovo();
				
			}
			
		});

		btnNovo.setVisible(true);

		pnlRodape.add(btnNovo);
		pnlRodape.add(btnFechar);
		
		
		// Definições página
		
		BorderLayout borderLayout = new BorderLayout();
		
				
		this.setLayout(borderLayout);
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlRodape, BorderLayout.SOUTH);
		
	}
	
	
	// métodos abstratos
	
	protected abstract void getTelaNovo();	
	protected abstract void mouseClickedTab();
	protected abstract void carregarGridItens(List<T> listaGenerics);
		
	
	// getters and setters
	
	protected JTable getTabGeneric() {
		return tabGeneric;
	}

	protected DefaultTableModel getModeloTabGeneric() {
		return modeloTabGeneric;
	}

	
}
