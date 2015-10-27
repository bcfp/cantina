package ui;

import interfaces.ITelaBuscar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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

import ui.templates.BuscarDialogView;
import ui.templates.ManterPanelView;
import utils.BancoFake;
import vo.FornecedorVO;
import vo.GenericVO;
import vo.MateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import enumeradores.TipoSolicitacao;
	
	public class ManterProdutoView extends ManterPanelView<ProdutoVO> implements ITelaBuscar{
		
		// Atributos Principais da Tela
		
		private JPanel pnlCampos;

		private JLabel lblCod;
		private JLabel lblNome;
		private JLabel lblLote;
		private JLabel lblTipoProduto;
		private JLabel lblMatPrima;
		private JLabel lblProdProduzido;
		private JLabel lblProdRevenda;

		private JTextField txtCod;
		private JTextField txtNome;
		
		private JRadioButton rdoLote;
		private JRadioButton rdoMatPrima;
		private JRadioButton rdoProdProduzido;
		private JRadioButton rdoProdRevenda;
		
		private ButtonGroup btgTipoProdutoGroup;
	
	
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
		
		private JLabel lblCodMatPrimaRec;
		private JLabel lblMatPrimaRec;
		private JLabel lblQtdeMatPrima;
		private JLabel lblUnidMatPrima;
		
		private JTextField txtCodMatPrimaRec;
		private JTextField txtMatPrimaRec;
		private JTextField txtQtdeMatPrima;
		
		private JComboBox<String> cbxUnidMatPrima;
		
		private JButton btnBuscarMatPrima;
		private JButton btnAdicionarMatPrima;
		
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

		private JTable tabLotes;
		private DefaultTableModel modeloTabLotes;
		private JScrollPane barraTabLotes;

		
		// ITelaBusca
		
		private String acaoPesquisar;
		private static final String PESQ_MAT_PRIMA = "materiaPrima";
		private static final String PESQ_FORNECEDOR = "fornecedor";
		
		
		// Atributos
		
		private ProdutoVendaVO produtoFabricado;
		private MateriaPrimaVO materiaPrima;
		private ProdutoMateriaPrimaVO prodMatPrima;
		private List<ProdutoMateriaPrimaVO> receita;		
		
		{
			
			receita = new ArrayList<ProdutoMateriaPrimaVO>();
			
			// PRINCIPAL
			
			int widthCampos = this.getWidth() - 25;
			int heightCampos = this.getHeight() - 122;
			
			tbsProdutos = new JTabbedPane();
			int yTbsProd = 150;
			tbsProdutos.setBounds(10, yTbsProd, widthCampos-20, heightCampos-yTbsProd-10);

			pnlCampos = new JPanel();
			pnlCampos.setBackground(Color.WHITE);
			pnlCampos.setBounds(10, 10, widthCampos, heightCampos);
			pnlCampos.setLayout(null);
			
			
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
			
			int espXLblDad = 20;
			int espXTxtDad = 120;

			lblTitValores.setBounds(espXLblDad, espY, 100, altura);
			lblPrecoCusto.setBounds(espXLblDad, espY + espEntre, 100, altura);
			lblPrecoVenda.setBounds(espXLblDad, espY + espEntre * 2, 100, altura);
			
			txtPrecoVenda.setBounds(espXTxtDad, espY + espEntre, 80, altura);
			txtPrecoCusto.setBounds(espXTxtDad, espY + espEntre * 2, 80, altura);
			
			int espXLblDad2 = 350;
			int espXTxtDad2 = 440;

			lblTitEstoque.setBounds(espXLblDad2, espY, 80, altura);
			lblQtdeMin.setBounds(espXLblDad2, espY + espEntre, 80, altura);
			lblQtdeMax.setBounds(espXLblDad2, espY + espEntre * 2, 80, altura);
			lblUnidade.setBounds(espXLblDad2,  espY + espEntre * 3, 80, altura);
			
			txtQtdeMin.setBounds(espXTxtDad2, espY + espEntre, 100, altura);
			txtQtdeMax.setBounds(espXTxtDad2,  espY + espEntre * 2, 100, altura);
			cbxUnidade.setBounds(espXTxtDad2, espY + espEntre * 3, 100, altura);
						
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
			
			lblCodMatPrimaRec = new JLabel("Código");
			lblMatPrimaRec = new JLabel("Matéria Prima");
			lblQtdeMatPrima = new JLabel("Quantidade");
			lblUnidMatPrima = new JLabel("Unidade");

			txtCodMatPrimaRec = new JTextField();
			txtMatPrimaRec = new JTextField();
			txtQtdeMatPrima = new JTextField();
			
			cbxUnidMatPrima = new JComboBox<String>();
			
			btnBuscarMatPrima = new JButton("Buscar");
			btnAdicionarMatPrima = new JButton(" + ");
						
			int espXLblRec = 20;
			int espXTxtRec = 120;

			lblCodMatPrimaRec.setBounds(espXLblRec, espY, 50, altura);
			lblMatPrimaRec.setBounds(espXLblRec, espY + espEntre, 80, altura);
			lblQtdeMatPrima.setBounds(espXLblRec, espY + espEntre * 2, 80, altura);

			txtCodMatPrimaRec.setBounds(espXTxtRec, espY, 50, altura);
			btnBuscarMatPrima.setBounds(espXTxtRec + 60, espY, 80, altura);
			txtMatPrimaRec.setBounds(espXTxtRec, espY + espEntre, 170, altura);
			txtQtdeMatPrima.setBounds(espXTxtRec, espY + espEntre * 2, 50, altura);
			btnAdicionarMatPrima.setBounds(espXTxtRec + 60, espY + espEntre * 2, 50, altura);
			
			int espXLblRec2 = 350;
			int espXTxtRec2 = 420;

			lblUnidMatPrima.setBounds(espXLblRec2, espY, 50, altura);
			
			cbxUnidMatPrima.setBounds(espXTxtRec2, espY, 120, altura);
			
			btnBuscarMatPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					acaoPesquisar = PESQ_MAT_PRIMA;

					new BuscarDialogView(ManterProdutoView.this, new String[]{"Código", "Matéria Prima", "Unidade"}).abrirJanela();
					
				}
				
			});
			
			btnAdicionarMatPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(materiaPrima!=null){
						
						if(adicionarMatPrima(materiaPrima)){
						
							txtCodMatPrimaRec.setText("");
							txtMatPrimaRec.setText("");
							txtQtdeMatPrima.setText("");
							//cbxUnidMatPrima.setSelectedIndex(0);
							
							btnAdicionarMatPrima.setEnabled(false);
						
						}
					}
					
				}
				
			});
			
			// Tabela
			tabMatPrimas = new JTable();
			modeloTabMatPrimas = new DefaultTableModel() {
				@Override public boolean isCellEditable(int row, int column) { return false; }
			};
			modeloTabMatPrimas.setColumnIdentifiers(new String[] {"Código", "Matéria-Prima", "Quantidade", "Unidade"});
			tabMatPrimas.setModel(modeloTabMatPrimas);
			barraTabMatPrimas = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			barraTabMatPrimas.setViewportView(tabMatPrimas);
			int yTabMatPrima = 130;
			barraTabMatPrimas.setBounds(0, yTabMatPrima, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabMatPrima);
			
			tabMatPrimas.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(tabMatPrimas.getSelectedRow() != -1 && e.getClickCount() == 2){
						
						int x = JOptionPane.showConfirmDialog(null, 
															"Deseja realmente excluir a matéria-prima?", 
															"Confirmação", 
															JOptionPane.YES_OPTION);
						
						if(x== JOptionPane.YES_NO_OPTION){
							
							receita.remove(tabMatPrimas.getSelectedRow());
							carregarGridMatPrima(receita);
							
						}
						
					}
					
				}
			});
			
			pnlReceita.add(barraTabMatPrimas);
			pnlReceita.add(lblCodMatPrimaRec);
			pnlReceita.add(lblMatPrimaRec);
			pnlReceita.add(lblQtdeMatPrima);
			pnlReceita.add(lblUnidMatPrima);
			pnlReceita.add(txtCodMatPrimaRec);
			pnlReceita.add(txtMatPrimaRec);
			pnlReceita.add(txtQtdeMatPrima);
			pnlReceita.add(cbxUnidMatPrima);
			pnlReceita.add(btnBuscarMatPrima);
			pnlReceita.add(btnAdicionarMatPrima);
			
			// FORNECEDORES
			
			pnlFornecedores = new JPanel();
			pnlFornecedores.setLayout(null);
			
			// Tabela forn
			tabForn = new JTable();
			modeloTabForn = new DefaultTableModel() {
				@Override public boolean isCellEditable(int row, int column) { return false; }
			};
			modeloTabForn.setColumnIdentifiers(new String[] {"Código", "Fornecedor", "Contato"});
			tabForn.setModel(modeloTabForn);
			barraTabForn = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			barraTabForn.setViewportView(tabForn);
			int yTabForn = 130;
			barraTabForn.setBounds(0, yTabForn, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabForn);

			pnlFornecedores.add(barraTabForn);
			
			
			// LOTES
			
			pnlLotes = new JPanel();
			pnlLotes.setLayout(null);
			
			// Tabela lote
			tabLotes = new JTable();
			modeloTabLotes = new DefaultTableModel() {
				@Override public boolean isCellEditable(int row, int column) { return false; }
			};
			modeloTabLotes.setColumnIdentifiers(new String[] {"Código", "Lote", "Qtde", "Vencimento"});
			tabLotes.setModel(modeloTabLotes);
			barraTabLotes = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			barraTabLotes.setViewportView(tabLotes);
			int yTabLotes = 130;
			barraTabLotes.setBounds(0, yTabLotes, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabLotes);
			
			pnlLotes.add(barraTabLotes);
			
						
			// TABS
			
			tbsProdutos.addTab("Dados", pnlDados);
			tbsProdutos.addTab("Receita", pnlReceita);
			tbsProdutos.addTab("Fornecedores", pnlFornecedores);
			tbsProdutos.addTab("Lotes", pnlLotes);
			
			tbsProdutos.setEnabledAt(1, false);
			tbsProdutos.setEnabledAt(2, false);
			tbsProdutos.setEnabledAt(3, false);
			
			btnAdicionarMatPrima.setEnabled(false);
			
			adicionarComponentesCentro(pnlCampos);
			
		}
		
		private boolean adicionarMatPrima(MateriaPrimaVO materiaPrima){
			
			String qtdeTxt = txtQtdeMatPrima.getText();
			
			if(qtdeTxt.trim().equals("")){
				
				JOptionPane.showMessageDialog(null, "Favor informar uma quantidade", "Campo Vazio", JOptionPane.YES_OPTION);
				
			}
			else{
				
				Double qtde = Double.parseDouble(qtdeTxt);
				
				if(qtde <= 0){
					
					JOptionPane.showMessageDialog(null, "Favor informar uma quantidade maior que zero", "Quantidade Inválida", JOptionPane.YES_OPTION);
					
				}
				else{
					
					Double qtdeInserida = qtde;
					Double qtdeReceita = 0d;
					int sizeReceita = receita.size();
					boolean itemNaReceita = false;
								
					prodMatPrima = new ProdutoMateriaPrimaVO();
					prodMatPrima.setMateriaPrima(materiaPrima);
					prodMatPrima.setQtde(qtdeInserida);
					
					for (int l = 0; l < sizeReceita; l++) {
			
						if (materiaPrima.getCodProduto() == receita.get(l).getMateriaPrima().getCodProduto()) {
			
							itemNaReceita = true;
							
							qtdeReceita = receita.get(l).getQtde();
			
							receita.get(l).setQtde(qtdeReceita + qtdeInserida);
			
						}
			
					}
					
					if(!itemNaReceita){
						receita.add(prodMatPrima);
					}
					
					carregarGridMatPrima(receita);
					
					return true;
				
				}
			
			}
			
			return false;
			
		}
		
		public void carregarGridMatPrima(List<ProdutoMateriaPrimaVO> receita) {
					
			modeloTabMatPrimas.setNumRows(0);
						
			if (receita != null) {
				
				String[] registro = new String[4];
				
				for (ProdutoMateriaPrimaVO itemReceita : receita) {
					
					registro[0] = itemReceita.getMateriaPrima().getCodProduto();
					registro[1] = itemReceita.getMateriaPrima().getDescricao();
					registro[2] = itemReceita.getQtde().toString();
					//registro[3] = cbxUnidMatPrima.getSelectedItem().toString();
					
					modeloTabMatPrimas.addRow(registro);
					
				}
	
			}
	
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
		
		
		// Métodos ITelaBuscar

		@Override
		public List<GenericVO> pesquisarItem(Map<String, String> parametros) {
			
			switch (acaoPesquisar) {
			
			case PESQ_MAT_PRIMA:
				
				return BancoFake.listaMatPrimaGeneric;
				
			case PESQ_FORNECEDOR:
			
				return BancoFake.listaFornecedorGeneric;

		}
		
			return null;
			
		}

		@Override
		public void carregarItemSelecionado(GenericVO item) {
			
			switch (acaoPesquisar) {
			
				case PESQ_MAT_PRIMA:
					
					btnAdicionarMatPrima.setEnabled(true);
					
					materiaPrima = (MateriaPrimaVO) item;

					txtCodMatPrimaRec.setText(materiaPrima.getCodProduto());
					txtMatPrimaRec.setText(materiaPrima.getDescricao());
					
				break;
					
				case PESQ_FORNECEDOR:
				
					
					
				break;
	
			}
			
		}

		@Override
		public String[] carregarGridTelaBusca(GenericVO item) {

			String[] registro = null;
			
			switch (acaoPesquisar) {
			
				case PESQ_MAT_PRIMA:
					
					MateriaPrimaVO materiaPriam = (MateriaPrimaVO) item; 
					
					registro = new String[3];

					registro[0] = materiaPriam.getCodProduto().toString();
					registro[1] = materiaPriam.getDescricao();
					registro[2] = materiaPriam.getUnidade().getAbreviatura();
										
				return registro;
					
				case PESQ_FORNECEDOR:
					
					FornecedorVO fornecedor = (FornecedorVO) item; 
					
					registro = new String[3];

					registro[0] = fornecedor.getCodFornecedor().toString();
					registro[1] = fornecedor.getNome();
					registro[2] = fornecedor.getContato();
					
					
				return registro;

			}
			
			return registro;
		}
		
	}
