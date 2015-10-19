package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import utils.BancoFake;
import vo.EstoqueProdutoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarEstoqueView extends JPanel{

	// Atributos da página
		
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlMenuLateral;
 
	private JButton btnGerarCompra;

	private JTable tabEstoque;
	private DefaultTableModel modeloTabEstoque;
	private JScrollPane barraTabEstoque;

	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private JButton btnFechar;	
	
	private JTextField txtCodProdPesq;
	private JTextField txtProdPesq;
	
	private JComboBox<String> cbxTipoProduto;
	
	private JCheckBox ccxAbaixoEstoque;
	
	private JLabel lblFiltrar;
	private JLabel lblCodProdPesq;
	private JLabel lblProdPesq;
	private JLabel lblTipoProduto;
	private JLabel lblAbaixoEstoque;
	
	private JButton btnPesquisar;
	
	public ConsultarEstoqueView() {
		criarPainel();
	}
	
	
	// Métodos concretos
	
	private void criarPainel(){
		
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
		lblTituloCabecalho.setText("Estoque");
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		
		
		// Filtro
		
		txtCodProdPesq = new JTextField();
		txtProdPesq = new JTextField();
		
		cbxTipoProduto = new JComboBox<String>();
		
		ccxAbaixoEstoque = new JCheckBox();
		
		lblFiltrar = new JLabel("Filtrar");
		lblCodProdPesq = new JLabel("Código");
		lblProdPesq = new JLabel("Produto");
		lblTipoProduto = new JLabel("Tipo");
		lblAbaixoEstoque = new JLabel("Abaixo do Estoque");
		
		btnPesquisar = new JButton("Filtrar");
		
		
		// Tabela de produtos
		
		tabEstoque = new JTable();
		modeloTabEstoque = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		barraTabEstoque = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		modeloTabEstoque.setColumnIdentifiers(new String[] {

				"Código","Produto",	"Tipo",	"Qtde", "Mínima", "Qtde Maxima"


		});
		
		modeloTabEstoque.setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<EstoqueProdutoVO> iEstoqueProduto = BancoFake.listaEstoqueProduto.iterator();
		
		while(iEstoqueProduto.hasNext()){
			
			EstoqueProdutoVO estoque = iEstoqueProduto.next();
			
			String[] registro = new String[6];

			registro[0] = estoque.getProduto().getCodProduto();
			registro[1] = estoque.getProduto().getDescricao();
			registro[2] = estoque.getProduto().getTipo().getTipoProduto();
			registro[3] = estoque.getQtdeAtual().toString();
			registro[4] = estoque.getQtdeMinima().toString();
			registro[5] = estoque.getQtdeMaxima().toString();
			
			modeloTabEstoque.addRow(registro);	
			
		}
		
		tabEstoque.setModel(modeloTabEstoque);		
		
		tabEstoque.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(tabEstoque.getSelectedRow() != -1){

					EstoqueProdutoVO estoque = BancoFake.listaEstoqueProduto.get(tabEstoque.getSelectedRow());	
															
					new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto").abrirJanela(estoque.getProduto());

				}
			}
		});
		
		barraTabEstoque.setViewportView(tabEstoque);
		
		barraTabEstoque.setBounds(10, 190, 645, 220);
		
		pnlCentro.add(barraTabEstoque);
		
		
		// BOTÕES
		
		btnGerarCompra = new JButton("Gerar OC");
		
		btnGerarCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Fazer tela intermediária para compras");
			}
			
		});
		
		pnlMenuLateral.add(btnGerarCompra);
		
		btnFechar = new JButton("X");
		btnFechar.setBackground(Color.RED);
		btnFechar.setForeground(Color.WHITE);	
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ConsultarEstoqueView.this.setVisible(false);
				ConsultarEstoqueView.this.getParent().removeAll();
				
			}
			
		});
		
		pnlCabecalho.add(btnFechar, BorderLayout.EAST);
		
		// Definições página
				
		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlMenuLateral, BorderLayout.WEST);
		this.setSize(750, 450);
		
	}

}
