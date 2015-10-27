	package ui;
	
	import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ui.templates.ManterPanelView;
import vo.ProdutoVO;
import vo.UnidadeProdutoVO;
import enumeradores.TipoSolicitacao;
	
	public class ManterProdutoView extends ManterPanelView<ProdutoVO>{
		
		// Atributos Principais da Tela
		
		private JPanel pnlCampos;
		private JLabel lblNome;
		private JTextField txtNome;
		private JLabel lblLote;
		private JRadioButton rdoLote;
		private JLabel lblTipoProduto;
		private JLabel lblMatPrima;
		private JLabel lblProdProduzido;
		private JLabel lblProdRevenda;
		private JRadioButton rdoMatPrima;
		private JRadioButton rdoProdProduzido;
		private JRadioButton rdoProdRevenda;
		private ButtonGroup btgTipoProdutoGroup;
		private JLabel lblCod;
		private JTextField txtCod;
	
	
		// Atributos da Tabs
		
		private JTabbedPane tbsProdutos;
		
		// DADOS
		
		private JPanel pnlDados;
		
		private JLabel lblTitValores;
		private JLabel lblPrecoCusto;
		private JLabel lblPrecoVenda;
		
		private JTextField txtPrecoCusto;
		private JTextField txtPrecoVenda;
		
		private JLabel lblTitEstoque;
		private JLabel lblQtdeMin;
		private JLabel lblQtdeMax;
		private JLabel lblUnidade;
		
		private JTextField txtQtdeMin;
		private JTextField txtQtdeMax;
		private JComboBox<String> cbxUnidade;
		
		
		// RECEITA
		
		private JPanel pnlReceita;

		private JTable tabMatPrimas;
		private DefaultTableModel modeloTabMatPrimas;
		private JScrollPane barraTabMatPrimas;
		
		
		// FORNECEDORES
		
		private JPanel pnlFornecedores;

		private JTable tabForn;
		private DefaultTableModel modeloTabForn;
		private JScrollPane barraTabForn;
		
		// LOTES
		
		private JPanel pnlLotes;
		
		private JLabel lblDiasVencimento;
		private JTextField txtDiasVencimento;

	
		
		
		{
			
			int widthCampos = this.getWidth() - 25;
			int heightCampos = this.getHeight() - 122;
			
			tbsProdutos = new JTabbedPane();
			int yTbsProd = 150;
			tbsProdutos.setBounds(10, yTbsProd, widthCampos-20, heightCampos-yTbsProd-10);

			pnlCampos = new JPanel();
			pnlCampos.setBackground(Color.WHITE);
			pnlCampos.setBounds(10, 10, widthCampos, heightCampos);
			pnlCampos.setLayout(null);
			
			
			// PRINCIPAL
			
			lblCod = new JLabel("Código");
			lblNome = new JLabel("Nome");
			lblLote = new JLabel("Lote");
			lblTipoProduto = new JLabel("TIPO");
			lblMatPrima = new JLabel("Matéria Prima");
			lblProdProduzido = new JLabel("Produto Produzido");
			lblProdRevenda = new JLabel("Produto Revenda");
			
			txtCod = new JTextField();
			txtNome = new JTextField();
			rdoLote = new JRadioButton();
			btgTipoProdutoGroup = new ButtonGroup();
			rdoMatPrima = new JRadioButton();
			rdoProdProduzido = new JRadioButton();
			rdoProdRevenda = new JRadioButton();
			
			txtCod.setEnabled(false);
			
			int espXLbl = 20;
			int espXTxt = espXLbl + 70;
			int espXLbl2 = 450;
			int espY = 20;
			int espEntre = 35;
			int altura = 30;			

			lblCod.setBounds(espXLbl, espY, 80, altura);
			lblNome.setBounds(espXLbl, espY + espEntre, 100, altura);
			lblLote.setBounds(espXLbl, espY + espEntre * 2, 100, altura);
			
			txtCod.setBounds(espXTxt, espY, 50, altura);
			txtNome.setBounds(espXTxt, espY + espEntre, 300, altura);
			rdoLote.setBounds(espXTxt - 30, espY + espEntre * 2, 80, altura);
			
			lblTipoProduto.setBounds(espXLbl2 - 20, espY, 200, altura);
			lblMatPrima.setBounds(espXLbl2, espY + espEntre, 150, altura);
			lblProdProduzido.setBounds(espXLbl2, espY + espEntre * 2, 150, altura);
			lblProdRevenda.setBounds(espXLbl2, espY + espEntre * 3, 150, altura);
						
			rdoMatPrima.setBounds(espXLbl2 - 20, espY + espEntre, 20, altura);
			rdoProdProduzido.setBounds(espXLbl2 - 20, espY + espEntre * 2, 20, altura);
			rdoProdRevenda.setBounds(espXLbl2 - 20, espY + espEntre * 3, 20, altura);
			
			rdoLote.setBackground(Color.WHITE);
			rdoLote.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(rdoLote.isSelected()){
						tbsProdutos.setEnabledAt(3, true);
					}
					else{
						tbsProdutos.setEnabledAt(3, false);
						tbsProdutos.setSelectedIndex(0);
					}
	
				}
			});
			
			rdoMatPrima.setBackground(Color.WHITE);
			rdoMatPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(rdoMatPrima.isSelected()){
						
						tbsProdutos.setEnabledAt(2, true);
						tbsProdutos.setEnabledAt(1, false);
						
						if(tbsProdutos.getSelectedComponent().equals(pnlReceita)){
							
							tbsProdutos.setSelectedComponent(pnlDados);
						}
					}
					
				}
			});
			
			rdoProdProduzido.setBackground(Color.WHITE);
			rdoProdProduzido.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(rdoProdProduzido.isSelected()){
						
						tbsProdutos.setEnabledAt(2, false);
						tbsProdutos.setEnabledAt(1, true);
						
						if(tbsProdutos.getSelectedComponent().equals(pnlFornecedores)){
							tbsProdutos.setSelectedComponent(pnlDados);
						}
						
					}
					
				}
			});
			
			rdoProdRevenda.setBackground(Color.WHITE);
			rdoProdRevenda.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(rdoProdRevenda.isSelected()){
						
						tbsProdutos.setEnabledAt(2, true);
						tbsProdutos.setEnabledAt(1, false);
						if(tbsProdutos.getSelectedComponent().equals(pnlReceita)){
							tbsProdutos.setSelectedComponent(pnlDados);
						}
					}
					
				}
			});
			
			btgTipoProdutoGroup.add(rdoMatPrima);
			btgTipoProdutoGroup.add(rdoProdProduzido);
			btgTipoProdutoGroup.add(rdoProdRevenda);

			pnlCampos.add(lblCod);
			pnlCampos.add(txtCod);
			pnlCampos.add(lblNome);
			pnlCampos.add(txtNome);
			pnlCampos.add(lblLote);
			pnlCampos.add(rdoLote);
			pnlCampos.add(lblTipoProduto);
			pnlCampos.add(lblMatPrima);
			pnlCampos.add(lblProdProduzido);
			pnlCampos.add(lblProdRevenda);
			pnlCampos.add(rdoMatPrima);
			pnlCampos.add(rdoProdProduzido);
			pnlCampos.add(rdoProdRevenda);
			pnlCampos.add(tbsProdutos);
			
			// TABS
			
			// DADOS
			
			pnlDados = new JPanel();
			pnlDados.setLayout(null);	

			lblTitValores = new JLabel();
			lblPrecoCusto = new JLabel();
			lblPrecoVenda = new JLabel();
			
			txtPrecoCusto = new JTextField();
			txtPrecoVenda = new JTextField();
			
			lblTitEstoque = new JLabel();
			lblQtdeMin = new JLabel();
			lblQtdeMax = new JLabel();
			lblUnidade = new JLabel();
						
			txtQtdeMax = new JTextField();
			txtQtdeMin = new JTextField();
			cbxUnidade = new JComboBox<String>();
			
			lblTitValores.setText("VALORES");
			lblPrecoCusto.setText("Preço de custo");
			lblPrecoVenda.setText("Preço de venda");
			lblUnidade.setText("Unidade");
			lblTitEstoque.setText("ESTOQUE");
			lblQtdeMin.setText("Qtde Minima");
			lblQtdeMax.setText("Qtde Maxima");
			
			int espXLblDados = 20;
			int espXTxtDados = 120;

			lblTitValores.setBounds(espXLblDados, espY, 100, altura);
			lblPrecoCusto.setBounds(espXLblDados, espY + espEntre, 100, altura);
			lblPrecoVenda.setBounds(espXLblDados, espY + espEntre * 2, 100, altura);
			
			txtPrecoVenda.setBounds(espXTxtDados, espY + espEntre, 80, altura);
			txtPrecoCusto.setBounds(espXTxtDados, espY + espEntre * 2, 80, altura);
			
			int espXLblDados2 = 350;
			int espXTxtDados2 = 440;

			lblTitEstoque.setBounds(espXLblDados2, espY, 80, altura);
			lblQtdeMin.setBounds(espXLblDados2, espY + espEntre, 80, altura);
			lblQtdeMax.setBounds(espXLblDados2, espY + espEntre * 2, 80, altura);
			lblUnidade.setBounds(espXLblDados2,  espY + espEntre * 3, 80, altura);
			
			txtQtdeMin.setBounds(espXTxtDados2, espY + espEntre, 100, altura);
			txtQtdeMax.setBounds(espXTxtDados2,  espY + espEntre * 2, 100, altura);
			cbxUnidade.setBounds(espXTxtDados2, espY + espEntre * 3, 100, altura);
						
			pnlDados.add(lblTitValores);
			pnlDados.add(lblPrecoCusto);
			pnlDados.add(txtPrecoCusto);
			pnlDados.add(lblPrecoVenda);
			pnlDados.add(txtPrecoVenda);
			pnlDados.add(lblUnidade);
			pnlDados.add(cbxUnidade);
			pnlDados.add(lblTitEstoque);
			pnlDados.add(lblQtdeMin);
			pnlDados.add(lblQtdeMax);
			pnlDados.add(txtQtdeMin);
			pnlDados.add(txtQtdeMax);
			
			
			// RECEITA
			
			pnlReceita = new JPanel();
			pnlReceita.setLayout(null);
			pnlReceita.setBackground(Color.CYAN);
			
			tabMatPrimas = new JTable();

			modeloTabMatPrimas = new DefaultTableModel() {

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

			};

			modeloTabMatPrimas.setColumnIdentifiers(new String[] {

					"Código", "Matéria-Prima", "Quantidade"

			});


			tabMatPrimas.setModel(modeloTabMatPrimas);

			barraTabMatPrimas = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			barraTabMatPrimas.setViewportView(tabMatPrimas);
			
			int yTabMatPrima = 130;

			barraTabMatPrimas.setBounds(0, yTabMatPrima, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabMatPrima);

			pnlReceita.add(barraTabMatPrimas);
			
			
			// FORNECEDORES
			
			pnlFornecedores = new JPanel();
			pnlFornecedores.setLayout(null);
			pnlFornecedores.setBackground(Color.DARK_GRAY);
			
			tabForn = new JTable();

			modeloTabForn = new DefaultTableModel() {

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

			};

			modeloTabForn.setColumnIdentifiers(new String[] {

					"Código", "Fornecedor", "Contato"

			});


			tabForn.setModel(modeloTabForn);

			barraTabForn = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			barraTabForn.setViewportView(tabForn);
			
			int yTabForn = 130;

			barraTabForn.setBounds(0, yTabForn, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabForn);

			pnlFornecedores.add(barraTabForn);
			
			
			// LOTES
			
			pnlLotes = new JPanel();
			pnlLotes.setLayout(null);
			pnlLotes.setBackground(Color.GRAY);
			
			
			
			tbsProdutos.addTab("Dados", pnlDados);
			tbsProdutos.addTab("Receita", pnlReceita);
			tbsProdutos.addTab("Fornecedores", pnlFornecedores);
			tbsProdutos.addTab("Lotes", pnlLotes);
			
			tbsProdutos.setEnabledAt(1, false);
			tbsProdutos.setEnabledAt(2, false);
			tbsProdutos.setEnabledAt(3, false);
			
			adicionarComponentesCentro(pnlCampos);
			
		}
		
		public ManterProdutoView(TipoSolicitacao solicitacao, String tituloCabecalho) {
			super(solicitacao, tituloCabecalho);
		}
		
		public ManterProdutoView(TipoSolicitacao solicitacao, String tituloCabecalho, List<ProdutoVO> produtos) {
			super(solicitacao, tituloCabecalho);
		}
		
		@Override
		public void abrirJanela() {
			
			this.setVisible(true);
			
		}

		@Override
		public void abrirJanela(ProdutoVO produto) {
			
			this.setVisible(true);
			
		}

		@Override
		public boolean incluir() {
			JOptionPane.showMessageDialog(null, "Produto Incluído");
			return false;
		}

		@Override
		public boolean alterar() {
			JOptionPane.showMessageDialog(null, "Produto Alterado");	
			return true;
		}

		@Override
		protected boolean habilitarCampos() {

			return false;
		
		}

		protected void desabilitarCampos(){
			
			
		}
		
		@Override
		protected void limparCampos() {
			
		}
		
	}
