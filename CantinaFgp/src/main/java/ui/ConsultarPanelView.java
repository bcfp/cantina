package ui;

import interfaces.ITelaConsultar;
import interfaces.ITelaManter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.GenericVO;

public abstract class ConsultarPanelView<T extends GenericVO> extends JPanel implements ITelaConsultar<T>{
	
	
	// atributos
	
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlBotoes;

	private JTable tabGeneric;
	private DefaultTableModel modeloTabGeneric;
	private JScrollPane barraTabGeneric;

	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private JButton btnFechar;
	private JButton btnNovo;
	
	
	
	// Construtor
	
	public ConsultarPanelView(String tituloCabecalho, String[] titulos, List<T> listaGenericos, int espX, int espY, int larg, int alt) {
		
		criarPainel(tituloCabecalho, titulos, listaGenericos, espX, espY, larg, alt);
		
	}
	
	// métodos concretos
	
	private void criarPainel(String tituloCabecalho, String[] titulos, List<T> listaGenericos, int espX, int espY, int larg, int alt){
		
		pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.BLACK);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setLayout(new BorderLayout());
		
		pnlCentro = new JPanel();	
		pnlCentro.setBackground(Color.LIGHT_GRAY);
		pnlCentro.setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(new GridLayout(10,1));
		pnlBotoes.setBackground(Color.WHITE);
		
		lblTituloCabecalho = new JLabel();
		lblTituloCabecalho.setText(tituloCabecalho);
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		
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
		
		carregarGridItens(listaGenericos);
		mouseClickedTab();
		
		
		// botões
		
		btnFechar = new JButton("X");
		btnFechar.setBackground(Color.RED);
		btnFechar.setForeground(Color.WHITE);	
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ConsultarPanelView.this.setVisible(false);
				
			}
			
		});
		
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.getTelaNovo();
				
			}
			
		});

		pnlBotoes.add(btnNovo);
		pnlCabecalho.add(btnFechar, BorderLayout.EAST);
		
		
		// Definições página
				
		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlBotoes, BorderLayout.WEST);
		this.setSize(750, 450);
		
	}
	
	// métodos
	
	protected void adicionarBotao(JButton botao){
		pnlBotoes.add(botao);
	}
	
	
	// métodos abstratos
	
	protected abstract void getTelaNovo();
	protected abstract void carregarGridItens(List<T> listaGenerics);
	
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					T item = (T) getItemDetalhar();
									
					new DialogConfirmacaoView<T>().abrirJanela(item, ConsultarPanelView.this, getTelaDetalhar());

				}
				
			}
			
		});
						
	}
	
	protected abstract GenericVO getItemDetalhar();
	protected abstract ITelaManter<T> getTelaDetalhar();
	
	// getters and setters
	
	
	protected JTable getTabGeneric() {
		return tabGeneric;
	}

	protected DefaultTableModel getModeloTabGeneric() {
		return modeloTabGeneric;
	}
	
}
