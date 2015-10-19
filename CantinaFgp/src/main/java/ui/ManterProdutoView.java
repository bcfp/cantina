	package ui;
	
	import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import sun.management.jdp.JdpGenericPacket;
import vo.ProdutoVO;
import vo.UnidadeProdutoVO;
import enumeradores.TipoSolicitacao;
	
	public class ManterProdutoView extends ManterDialogView<ProdutoVO>{
		
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
	
		private JTabbedPane tbsProdutos;
		
		// Atributos de tela de dados do produto
		private JPanel pnlDados;
		private JLabel lblPrecoCusto;
		private JTextField txtPrecoCusto;
		private JLabel lblUnidade;
		private JComboBox<UnidadeProdutoVO> comboUnidade;
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
			
			
			//Instanciação das tabs de navegação do produto
			tbsProdutos = new JTabbedPane();
			
			//Instanciação dos atributos de tela de dados especificos do produto
			pnlDados = new JPanel();
			lblPrecoCusto = new JLabel();
			txtPrecoCusto = new JTextField();
			lblUnidade = new JLabel();
			comboUnidade = new JComboBox<UnidadeProdutoVO>();
			
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
			return false;
		}

		@Override
		protected boolean habilitarCampos() {

			return false;
		
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
			
			lblNome.setText("Nome:");
			lblNome.setBounds(10,20,100,30);
			
			txtNome.setBounds(50,20,100,30);
			
			lblLote.setText("Lote:");
			lblLote.setBounds(10,60,100,30);
			
			radioLote.setBounds(50,60,50,30);
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
			
			lblTipoProduto.setText("Tipo:");
			lblTipoProduto.setBounds(200, 20, 200, 30);
			
			lblMP.setText("Matéria Prima");
			lblMP.setBounds(200,60,150,30);
			
			radioMP.setBackground(Color.WHITE);
			radioMP.setBounds(280,60,30,30);
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
			
			lblPP.setText("Produto Produzido");
			lblPP.setBounds(320, 60, 150, 30);
			
			radioPP.setBackground(Color.WHITE);
			radioPP.setBounds(430,60,30,30);
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
			
			lblPR.setText("Produto Revenda");
			lblPR.setBounds(470,60,150,30);
			
			radioPR.setBackground(Color.WHITE);
			radioPR.setBounds(570,60,30,30);
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
			
			int yTbsProd = 150;
		
			tbsProdutos.setBounds(10, yTbsProd, widthCampos-20, heightCampos-yTbsProd-10);
			
			//Campos da tela dados do produto
			pnlDados.setLayout(null);	
			pnlDados.setBackground(Color.BLUE);
			
			lblPrecoCusto.setText("Preço de Custo:");

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
			
			incluirComponenteCentro(pnlCampos);
			
		}
	
	}
