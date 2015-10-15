	package ui;
	
	import java.awt.Color;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.ProdutoVO;
import enumeradores.TipoSolicitacao;
	
	public class ManterProdutoView extends ManterDialogView<ProdutoVO>{
		
		private JPanel pnlCampos;
	
		private JTabbedPane tbsProdutos;

		private JPanel pnlDados;
		private JPanel pnlReceita;
		private JPanel pnlFornecedores;
		private JPanel pnlLotes;
		
		private JTable tabMatPrimas;
		private DefaultTableModel modeloTabMatPrimas;
		private JScrollPane barraTabMatPrimas;
		
		
		{
			
			int widthCampos = this.getWidth()  - 25;
			int heightCampos = this.getHeight() - 122;
			
			pnlCampos = new JPanel();
			pnlCampos.setBackground(Color.WHITE);
			pnlCampos.setBounds(10, 10, widthCampos, heightCampos);
			pnlCampos.setLayout(null);
			
			int yTbsProd = 150;
			
			tbsProdutos = new JTabbedPane();
			tbsProdutos.setBounds(10, yTbsProd, widthCampos-20, heightCampos-yTbsProd-10);
			
			pnlDados = new JPanel();
			pnlDados.setLayout(null);	
			pnlDados.setBackground(Color.BLUE);
			
			tbsProdutos.addTab("Dados", pnlDados);
			
			pnlReceita = new JPanel();
			pnlReceita.setLayout(null);
			pnlReceita.setBackground(Color.CYAN);
						
			tbsProdutos.addTab("Receita", pnlReceita);
			
			pnlFornecedores = new JPanel();
			pnlFornecedores.setLayout(null);
			pnlFornecedores.setBackground(Color.DARK_GRAY);
						
			tbsProdutos.addTab("Fornecedores", pnlFornecedores);
			
			pnlLotes = new JPanel();
			pnlLotes.setLayout(null);
			pnlLotes.setBackground(Color.GRAY);
						
			tbsProdutos.addTab("Lotes", pnlLotes);
			
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

			barraTabMatPrimas.setBounds(0, yTabMatPrima, tbsProdutos.getWidth(), 
					tbsProdutos.getHeight() - yTabMatPrima);

			pnlReceita.add(barraTabMatPrimas);
			
			pnlCampos.add(tbsProdutos);
			
			incluirComponenteCentro(pnlCampos);
			
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
			return false;
		}

		@Override
		protected boolean habilitarCampos() {

			return false;
		
		}
		
		@Override
		protected void limparCampos() {
			
		}	
	
	}
