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
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.GenericVO;


/**
 * Tela de consulta padrão. As demais telas de consulta devem herdar esta classe.
 * As telas de consulta são utilizadas para pesquisar e excluir itens do tipo passado por Generic Type.
 * 
 * @author bruno.silva
 *
 * @param <T> - Deve ser passado como generic type um objeto do tipo GenericVO. Este objeto será utilizado para 
 * definir o tipo dos parâmetros dos métodos da tela de consulta.
 */
public abstract class ConsultarPanelView<T extends GenericVO> extends JPanel implements ITelaConsultar<T>{
	
	
	// Atributos da Janela
	
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlMenuLateral;

	private JTable tabGeneric;
	private DefaultTableModel modeloTabGeneric;
	private JScrollPane barraTabGeneric;

	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private JButton btnFechar;
	private JButton btnNovo;
	
	// Lista de itens do tipo T para alimentar a tabela de consulta
	private List<T> listaGenericos;
	
	
	// Construtores
	
	/**
	 * @param tituloCabecalho - Título da jabela de consulta
	 * @param titulos - Títulos das colunas da tabela de consulta
	 * @param listaGenericos - Lista de itens do tipo definido no Generic Type que serão carregados na tabela
	 * @param espX - Distância da lateral esquerda da tabela de consulta
	 * @param espY - Distância do topo da tabela de consulta
	 * @param larg - Largura da tabela de consulta
	 * @param alt - Altura da tabela de consulta
	 */
	public ConsultarPanelView(String tituloCabecalho, String[] titulos, List<T> listaGenericos, int espX, int espY, int larg, int alt) {
		
		criarPainel(tituloCabecalho, titulos, listaGenericos, espX, espY, larg, alt);
		
	}
	
	
	// Métodos Concretos
	
	// Método utilizado para criar o panel de consulta
	private void criarPainel(String tituloCabecalho, String[] titulos, List<T> listaGenericos, int espX, int espY, int larg, int alt){
		
		pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.BLACK);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setLayout(new BorderLayout());
		
		pnlCentro = new JPanel();	
		pnlCentro.setBackground(Color.LIGHT_GRAY);
		pnlCentro.setLayout(null);
		
		pnlMenuLateral = new JPanel();
		pnlMenuLateral.setLayout(new GridLayout(10,1));
		pnlMenuLateral.setBackground(Color.WHITE);
		
		lblTituloCabecalho = new JLabel();
		lblTituloCabecalho.setText(tituloCabecalho);
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		
		setListaGenericos(listaGenericos);
		
		
		// Tabela de consulta
		
		tabGeneric = new JTable();
		modeloTabGeneric = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		barraTabGeneric = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Insere títulos passados no construtor pela classe filha
		modeloTabGeneric.setColumnIdentifiers(titulos);
		
		tabGeneric.setModel(modeloTabGeneric);
		
		barraTabGeneric.setViewportView(tabGeneric);
		
		// Defini posição e tamanho passados no construtor pela classe filha
		barraTabGeneric.setBounds(espX, espY, larg, alt);

		pnlCentro.add(barraTabGeneric);
		
		// Carrega tabela com a lista de itens pessados pela classe filha
		carregarGridItens(listaGenericos);
		
		// Defini ação ao clicar em um item da coluna
		mouseClickedTab();
		
		
		
		// BOTÕES
		
		btnFechar = new JButton("X");
		btnFechar.setBackground(Color.RED);
		btnFechar.setForeground(Color.WHITE);	
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ConsultarPanelView.this.setVisible(false);
				ConsultarPanelView.this.getParent().removeAll();
				
			}
			
		});
		
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.getTelaIncluir().abrirJanela(); // abre tela incluir retornada pela classe filha através do método getTelaIncluir
				
			}
			
		});

		pnlMenuLateral.add(btnNovo);
		pnlCabecalho.add(btnFechar, BorderLayout.EAST);
		
		
		// Definições página
				
		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlMenuLateral, BorderLayout.WEST);
		this.setSize(750, 450);
		
	}
	
	
	// Métodos
	
	/**
	 * Método utilizado para filha adicionar botão no menu lateral da tela
	 * 
	 * @param JButton - Botão que deverá ser inserido
	 */
	protected void adicionarBotao(JButton botao){
		pnlMenuLateral.add(botao);
	}
	
	/**
	 * Método para definir ação ao clicar em um item da tabela, método chamado por callback
	 */
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					// Traz o item do tipo T selecionado pelo usuário na tabela
					T item = ConsultarPanelView.this.getListaGenericos().get(getTabGeneric().getSelectedRow());	
															
					// Envia para o método abrirJanela da Dialog de opções o item selecionado, 
					// a janela de consulta solicitante e a telaAlterar (Manter) que deverá ser chamada caso o usuário clique em 'Detalhar'
					new DialogOpcoesView<T>().abrirJanela(item, ConsultarPanelView.this, getTelaAlterar());

				}
			}
		});
	}
	
	/**
	 * Insere na tabela os itens passados pela classe filha. 
	 * 
	 * @param listaItens - Lista de itens do tipo T
	 */
	private void carregarGridItens(List<T> listaItens) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<T> iItem = listaItens.iterator();
		
		while(iItem.hasNext()){
			
			T item = iItem.next();
			
			// Insere na tabela o item retornado do método abstrato carregarGridItens, implementado na classe filha 
			getModeloTabGeneric().addRow(carregarGridItens(item));	
			
		}
	}	
	
	
	// Métodos abstratos
	
	/**
	 * Insere item do tipo T na tabela de consulta.
	 * Ao implementar este método, deve ser feita a lógica para inserir os valores dos atributos do tipo T 
	 * que devem ser apresentados na tabela de consulta.
	 * 
	 * @param item - Item do tipo T que deverá ser inserido na tabela
	 * @return String[] - Deve ser retornado um vetor de String com os valores na ordem que deverão ser inseridos na tabela de consulta
	 */
	protected abstract String[] carregarGridItens(T item);
		
	/**
	 * Ao implementar este método, deve ser feita uma lógica para retornar uma tela de incluir um item do tipo T
	 * @return ITelaManter<T> - Retorna uma tela do tipo ITelaManter
	 */
	protected abstract ITelaManter<T> getTelaIncluir();

	/**
	 * Ao implementar este método, deve ser feita uma lógica para retornar uma tela de alterar um item do tipo T
	 * @return ITelaManter<T> - Retorna uma tela do tipo ITelaManter
	 */
	protected abstract ITelaManter<T> getTelaAlterar();
	
	
	
	// getters and setters
		
	protected JTable getTabGeneric() {
		return tabGeneric;
	}

	protected DefaultTableModel getModeloTabGeneric() {
		return modeloTabGeneric;
	}

	public List<T> getListaGenericos() {
		return listaGenericos;
	}

	public void setListaGenericos(List<T> listaGenericos) {
		this.listaGenericos = listaGenericos;
	}
	
}
