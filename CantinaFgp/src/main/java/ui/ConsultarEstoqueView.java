package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

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
import vo.CantinaVO;
import vo.EstoqueProdutoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarEstoqueView extends JPanel{

	// Atributos da página
		
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlMenuLateral;
	private JPanel pnlRodape;
 
	private JButton btnGerarCompra;

	private JTable tabEstoque;
	private DefaultTableModel modeloTabEstoque;
	private JScrollPane barraTabEstoque;

	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private JButton btnFechar;	
	
	private JTextField txtCodProdPesq;
	private JTextField txtDescricaoProd;
	
	private JComboBox<String> cbxTipoProduto;
	
	private JCheckBox ccxAbaixoEstoque;
	
	private JLabel lblFiltrar;
	private JLabel lblCodProdPesq;
	private JLabel lblProdPesq;
	private JLabel lblTipoProduto;
	
	private JButton btnConsultar;
	
	private List<EstoqueProdutoVO> estoqueProdutos;
	
	
	// Construtor
	
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
		
		pnlRodape = new JPanel();
		pnlRodape.setBackground(Color.WHITE);
		
		lblTituloCabecalho = new JLabel();
		lblTituloCabecalho.setText("Estoque");
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		
		
		// Filtro / Consulta
		
		lblFiltrar = new JLabel("FILTRAR");
		lblCodProdPesq = new JLabel("Código");
		lblProdPesq = new JLabel("Descrição");
		lblTipoProduto = new JLabel("Tipo");
		
		txtCodProdPesq = new JTextField();
		
		txtDescricaoProd = new JTextField();
		
		cbxTipoProduto = new JComboBox<String>();
		
		ccxAbaixoEstoque = new JCheckBox("Abaixo do Estoque");
		ccxAbaixoEstoque.setBackground(pnlCentro.getBackground());
		ccxAbaixoEstoque.setFocusPainted(false);
		
		int espXLbl = 20;
		int espXTxt = 110;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblFiltrar.setBounds(espXLbl, espY, 50, altura);
		lblCodProdPesq.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblProdPesq.setBounds(espXLbl, espY + espEntre * 2, 80, altura);
		lblTipoProduto.setBounds(espXLbl, espY + espEntre * 3, 50, altura);

		txtCodProdPesq.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtDescricaoProd.setBounds(espXTxt, espY + espEntre * 2, 250, altura);
		cbxTipoProduto.setBounds(espXTxt, espY + espEntre * 3, 150, altura);
		ccxAbaixoEstoque.setBounds(espXTxt + 310, espY + espEntre, 150, altura);
		
		pnlCentro.add(lblFiltrar);
		pnlCentro.add(lblCodProdPesq);
		pnlCentro.add(lblProdPesq);
		pnlCentro.add(lblTipoProduto);
		pnlCentro.add(txtCodProdPesq);
		pnlCentro.add(txtDescricaoProd);
		pnlCentro.add(cbxTipoProduto);
		pnlCentro.add(ccxAbaixoEstoque);
		
		
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

		//carregarGridItens(BancoFake.listaEstoqueProduto);
		
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
		
		barraTabEstoque.setBounds(10, 190, 645, 185);
		
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

				setVisible(false);
				getParent().removeAll();
				
			}
			
		});
		
		pnlCabecalho.add(btnFechar, BorderLayout.EAST);
				
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setListaEstoqueProdutos(consultar());				
				carregarGridItens(getEstoqueProdutos());
				
			}
		});
		
		pnlCentro.add(btnConsultar);
		
		pnlRodape.add(btnConsultar);
		
		// Definições página
				
		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlMenuLateral, BorderLayout.WEST);
		this.add(pnlRodape, BorderLayout.SOUTH);
		this.setSize(750, 450);
		
	}
	
	/**
	 * Insere na tabela os itens passados como parâmetro. 
	 * 
	 * @param estoqueProduto - 
	 */
	private void carregarGridItens(List<EstoqueProdutoVO> estoqueProduto) {

		modeloTabEstoque.setNumRows(0);
		
		if(estoqueProduto != null){
			
			Iterator<EstoqueProdutoVO> iEstoqueProduto = estoqueProduto.iterator();
			
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
		}
		
	}

	public List<EstoqueProdutoVO> consultar() {
		return BancoFake.listaEstoqueProduto;
	}


	public List<EstoqueProdutoVO> getEstoqueProdutos() {
		return estoqueProdutos;
	}


	public void setListaEstoqueProdutos(List<EstoqueProdutoVO> estoqueProdutos) {
		this.estoqueProdutos = estoqueProdutos;
	}

}