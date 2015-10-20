package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import enumeradores.TipoSolicitacao;
import utils.BancoFake;
import vo.EstoqueProdutoVO;
import vo.ProdutoVO;

public class GerarCompra extends JDialog {

	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlRodape;
	private JLabel lblTituloCabecalho;
	
	private JButton btnGerarCompra;
	
	private List<ProdutoVO> listaProdutos;

	private JTable tabProdCompra;
	private DefaultTableModel modeloTabProdCompra;
	private JScrollPane barraTabProdCompra;

	public void abrirJanela(List<ProdutoVO> listaProdutos) {

		this.listaProdutos = listaProdutos;

		lblTituloCabecalho = new JLabel("Gerar Compra");
		
		pnlCabecalho = new JPanel();
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		pnlCabecalho.setBackground(Color.BLACK);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setLayout(new BorderLayout());
				
		pnlRodape = new JPanel();
		pnlRodape.setBackground(Color.WHITE);
		
		btnGerarCompra = new JButton("Gerar");
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

		barraTabProdCompra.setBounds(10, 190, 645, 185);
		
		pnlCentro = new JPanel();
		pnlCentro.add(barraTabProdCompra);
		
		definicoesPagina();

	}

	private void definicoesPagina() {

		this.setLayout(new BorderLayout());		
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlRodape, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setSize(700, 600);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
