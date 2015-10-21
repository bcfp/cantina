package ui;

import interfaces.IGeradorCompra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import enumeradores.TipoSolicitacao;
import utils.BancoFake;
import vo.CompraVO;
import vo.EstoqueProdutoVO;
import vo.ItemCompraVO;
import vo.ProdutoVO;

public class GerarCompraView extends JDialog {

	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlRodape;
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	
	private JCheckBox ccxListarTodos;
	
	private JButton btnGerarCompra;
	
	private List<ItemCompraVO> listaItensCompra;

	private JTable tabProdCompra;
	private DefaultTableModel modeloTabProdCompra;
	private JScrollPane barraTabProdCompra;

	private IGeradorCompra geradorCompra;
	
	public void abrirJanela(IGeradorCompra geradorCompra, List<ItemCompraVO> listaItensCompra) {

		this.geradorCompra = geradorCompra;
		this.setListaItensCompra(listaItensCompra);

		lblTituloCabecalho = new JLabel("Gerar Compra");
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		
		pnlCabecalho = new JPanel();
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		pnlCabecalho.setBackground(Color.BLACK);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		pnlRodape = new JPanel();
		pnlRodape.setBackground(Color.WHITE);
		
		btnGerarCompra = new JButton("Gerar");
		btnGerarCompra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra").abrirJanela(
						new CompraVO(GerarCompraView.this.geradorCompra, getListaItensCompra()));
				
			}
		});
		
		pnlRodape.add(btnGerarCompra);
		
		// Tabela de produtos

		tabProdCompra = new JTable();
		modeloTabProdCompra = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		barraTabProdCompra = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		modeloTabProdCompra.setColumnIdentifiers(new String[] {

				"Código", "Produto"

		});

		modeloTabProdCompra.setNumRows(0); 

		// carregarGridItens(BancoFake.listaEstoqueProduto);

		tabProdCompra.setModel(modeloTabProdCompra);

		tabProdCompra.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (tabProdCompra.getSelectedRow() != -1) {

					EstoqueProdutoVO estoque = BancoFake.listaEstoqueProduto.get(tabProdCompra.getSelectedRow());

					new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto").abrirJanela(estoque.getProduto());

				}
			}
		});

		barraTabProdCompra.setViewportView(tabProdCompra);

		barraTabProdCompra.setBounds(10, 40, 475, 290);
		
		pnlCentro = new JPanel();
		
		ccxListarTodos = new JCheckBox("Listar Todos");
		ccxListarTodos.setFocusPainted(false);
		ccxListarTodos.setBounds(10, 10, 100, 20);
		
		
		pnlCentro.setLayout(null);
		pnlCentro.add(barraTabProdCompra);
		pnlCentro.add(ccxListarTodos);
		
		definicoesPagina();

	}

	private void definicoesPagina() {

		this.setLayout(new BorderLayout());		
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlRodape, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setSize(500, 450);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public List<ItemCompraVO> getListaItensCompra() {
		return listaItensCompra;
	}

	public void setListaItensCompra(List<ItemCompraVO> listaItensCompra) {
		this.listaItensCompra = listaItensCompra;
	}

}
