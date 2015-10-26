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
		
		//Atributos de tela principal do produto
		private JPanel pnlCampos;
		private JLabel lblNome;
		private JTextField txtNome;
		private JLabel lblLote;
		private JRadioButton radioLote;
		private JLabel lblTipoProduto;
		private JLabel lblMP;
		private JLabel lblPP;
		private JLabel lblPR;
		private JRadioButton radioMP;
		private JRadioButton radioPP;
		private JRadioButton radioPR;
		private ButtonGroup btnTipoProdutoGroup;
		private JLabel lblCod;
		private JTextField txtCod;
	
		private JTabbedPane tbsProdutos;
		
		//Atributos de tela de dados do produto
		private JPanel pnlDados;
		private JLabel lblPrecoCusto;
		private JTextField txtPrecoCusto;
		private JLabel lblUnidade;
		private JComboBox<String> comboUnidade;
		private JLabel lblPrecoVenda;
		private JTextField txtPrecoVenda;
		private JLabel lblDiasVencimento;
		private JTextField txtDiasVencimento;
		
		
		private JPanel pnlReceita;
		private JPanel pnlFornecedores;
		private JPanel pnlLotes;
		
		
		private JTable tabMatPrimas;
		private DefaultTableModel modeloTabMatPrimas;
		private JScrollPane barraTabMatPrimas;
		
		
		{
			//Instanciação de atributos de tela principal
			pnlCampos = new JPanel();
			lblNome = new JLabel();
			txtNome = new JTextField();
			lblLote = new JLabel();
			radioLote = new JRadioButton();
			lblDiasVencimento = new JLabel();
			txtDiasVencimento = new JTextField();
			lblPrecoVenda = new JLabel();
			txtPrecoVenda = new JTextField();
			lblTipoProduto = new JLabel();
			lblMP = new JLabel();
			lblPP = new JLabel();
			lblPR = new JLabel();
			radioMP = new JRadioButton();
			radioPP = new JRadioButton();
			radioPR = new JRadioButton();
			btnTipoProdutoGroup = new ButtonGroup();
			lblCod = new JLabel();
			txtCod = new JTextField();
			
			
			//Instanciação das tabs de navegação do produto
			tbsProdutos = new JTabbedPane();
			
			//Instanciação dos atributos de tela de dados especificos do produto
			pnlDados = new JPanel();
			lblPrecoCusto = new JLabel();
			txtPrecoCusto = new JTextField();
			lblUnidade = new JLabel();
			comboUnidade = new JComboBox<String>();
			
			pnlReceita = new JPanel();
			pnlFornecedores = new JPanel();
			pnlLotes = new JPanel();
			tabMatPrimas = new JTable();
			
		}
		
		public ManterProdutoView(TipoSolicitacao solicitacao, String tituloCabecalho) {
			super(solicitacao, tituloCabecalho);
		}
		
		public ManterProdutoView(TipoSolicitacao solicitacao, String tituloCabecalho, List<ProdutoVO> produtos) {
			super(solicitacao, tituloCabecalho);
		}
		
		@Override
		public void abrirJanela() {
			
			definicoesPagina();
			this.setVisible(true);
			
		}

		@Override
		public void abrirJanela(ProdutoVO produto) {
			
			definicoesPagina();
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
		
		private void definicoesPagina(){
			
			int widthCampos = this.getWidth()  - 25;
			int heightCampos = this.getHeight() - 122;
			
			//Campos da tela principal de produtos
			pnlCampos.setBackground(Color.WHITE);
			pnlCampos.setBounds(10, 10, widthCampos, heightCampos);
			pnlCampos.setLayout(null);
			
			lblNome.setText("Nome");
			lblLote.setText("Lote");
			lblCod.setText("Código:");
			lblTipoProduto.setText("Tipo do produto:");
			lblMP.setText("Matéria Prima");
			lblPP.setText("Produto Produzido");
			lblPR.setText("Produto Revenda"); 
			
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
			radioLote.setBounds(espXTxt - 30, espY + espEntre * 2, 80, altura);
			
			lblTipoProduto.setBounds(espXLbl2 - 20, espY, 200, altura);
			lblMP.setBounds(espXLbl2, espY + espEntre, 150, altura);
			lblPP.setBounds(espXLbl2, espY + espEntre * 2, 150, altura);
			lblPR.setBounds(espXLbl2, espY + espEntre * 3, 150, altura);
						
			radioMP.setBounds(espXLbl2 - 20, espY + espEntre, 20, altura);
			radioPP.setBounds(espXLbl2 - 20, espY + espEntre * 2, 20, altura);
			radioPR.setBounds(espXLbl2 - 20, espY + espEntre * 3, 20, altura);
			

			
			
			radioLote.setBackground(Color.WHITE);
			radioLote.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(radioLote.isSelected()){
						
						tbsProdutos.setEnabledAt(3, true);
					}
					else{
						tbsProdutos.setEnabledAt(3, false);
						tbsProdutos.setSelectedIndex(0);
					}
	
				}
			});
			
			txtCod.setEnabled(false);
			
			radioMP.setBackground(Color.WHITE);
			radioMP.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(radioMP.isSelected()){
						
						tbsProdutos.setEnabledAt(2, true);
						tbsProdutos.setEnabledAt(1, false);
						
						if(tbsProdutos.getSelectedComponent().equals(pnlReceita)){
							
							tbsProdutos.setSelectedComponent(pnlDados);
						}
					}
					
				}
			});
			
			
			radioPP.setBackground(Color.WHITE);
			radioPP.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(radioPP.isSelected()){
						
						tbsProdutos.setEnabledAt(2, false);
						tbsProdutos.setEnabledAt(1, true);
						
						if(tbsProdutos.getSelectedComponent().equals(pnlFornecedores)){
							
							tbsProdutos.setSelectedComponent(pnlDados);
						}
						
					}
					
				}
			});
			
			
			radioPR.setBackground(Color.WHITE);
			radioPR.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(radioPR.isSelected()){
						
						tbsProdutos.setEnabledAt(2, true);
						tbsProdutos.setEnabledAt(1, false);
						if(tbsProdutos.getSelectedComponent().equals(pnlReceita)){
							
							tbsProdutos.setSelectedComponent(pnlDados);
						}
					}
					
				}
			});
			
			btnTipoProdutoGroup.add(radioMP);
			btnTipoProdutoGroup.add(radioPP);
			btnTipoProdutoGroup.add(radioPR);
			
			pnlCampos.add(lblNome);
			pnlCampos.add(txtNome);
			pnlCampos.add(lblLote);
			pnlCampos.add(radioLote);
			pnlCampos.add(lblTipoProduto);
			pnlCampos.add(lblMP);
			pnlCampos.add(radioMP);
			pnlCampos.add(lblPP);
			pnlCampos.add(radioPP);
			pnlCampos.add(lblPR);
			pnlCampos.add(radioPR);
			pnlCampos.add(lblCod);
			pnlCampos.add(txtCod);
			
			int yTbsProd = 150;
		
			tbsProdutos.setBounds(10, yTbsProd, widthCampos-20, heightCampos-yTbsProd-10);
			
			//Campos da tela dados do produto
			pnlDados.setLayout(null);	
			
			lblPrecoCusto.setText("Preço de custo:");
			lblPrecoCusto.setBounds(10,20,100,30);
			
			
			lblPrecoVenda.setText("Preço de venda:");
			lblPrecoVenda.setBounds(10, 60, 100, 30);
			
			txtPrecoVenda.setBounds(110, 60, 80, 30);
			
			lblUnidade.setText("Unidade:");
			lblUnidade.setBounds(220, 20, 100 ,30);
			
			comboUnidade.setBounds(330, 20, 100, 30);
			
			List<UnidadeProdutoVO> listaUnidades = new ArrayList<UnidadeProdutoVO>();
			
			UnidadeProdutoVO unidade = new UnidadeProdutoVO();
			unidade.setStatus(true);
			unidade.setDescricao("Litro");
			unidade.setAbreviatura("L");
			unidade.setIdUnidadeProduto(1l); 
			
			listaUnidades.add(unidade);
			
			unidade = new UnidadeProdutoVO();
			unidade.setStatus(true);
			unidade.setDescricao("Kilo");
			unidade.setAbreviatura("Kg");
			unidade.setIdUnidadeProduto(2l); 
			
			listaUnidades.add(unidade);
			
			Iterator<UnidadeProdutoVO> iteratorUnidade = listaUnidades.iterator();
			
			UnidadeProdutoVO unidadeTeste = new UnidadeProdutoVO();
			
			while(iteratorUnidade.hasNext()){	
				unidadeTeste = iteratorUnidade.next();
				comboUnidade.addItem(unidadeTeste.getAbreviatura());
			}

			
			lblDiasVencimento.setText("Dias para o vencimento:");
			lblDiasVencimento.setBounds(10,100, 150, 30);
			
			txtDiasVencimento.setBounds(160,100,80,30);
			
			pnlDados.add(lblPrecoCusto);
			pnlDados.add(txtPrecoCusto);
			pnlDados.add(lblPrecoVenda);
			pnlDados.add(txtPrecoVenda);
			pnlDados.add(lblUnidade);
			pnlDados.add(comboUnidade);
			pnlDados.add(lblDiasVencimento);
			pnlDados.add(txtDiasVencimento);
			
			tbsProdutos.addTab("Dados", pnlDados);

			pnlReceita.setLayout(null);
			pnlReceita.setBackground(Color.CYAN);
						
			tbsProdutos.addTab("Receita", pnlReceita);

			pnlFornecedores.setLayout(null);
			pnlFornecedores.setBackground(Color.DARK_GRAY);
						
			tbsProdutos.addTab("Fornecedores", pnlFornecedores);

			pnlLotes.setLayout(null);
			pnlLotes.setBackground(Color.GRAY);
						
			tbsProdutos.addTab("Lotes", pnlLotes);
			
			tbsProdutos.setEnabledAt(1, false);
			tbsProdutos.setEnabledAt(2, false);
			tbsProdutos.setEnabledAt(3, false);

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

			barraTabMatPrimas.setBounds(0, yTabMatPrima, tbsProdutos.getWidth(), 
					tbsProdutos.getHeight() - yTabMatPrima);

			pnlReceita.add(barraTabMatPrimas);
			
			pnlCampos.add(tbsProdutos);
			
			adicionarComponentesCentro(pnlCampos);
			
		}
	
	}
