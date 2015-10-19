package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import vo.ProdutoVendaVO;
import bo.ProdutoVendaBO;

public class ConsultarProdutosDialogView extends JDialog{
	
	private JPanel pnlCentro;
	private JPanel pnlPesquisa;
	private JTable tabelaProdutos;
	private DefaultTableModel modeloTabProdutos;
	private JScrollPane barraTabProdutos;
	
	private ProdutoVendaBO produtoVendaBO;
	
	{
		pnlCentro = new JPanel();
		pnlPesquisa = new JPanel();
		tabelaProdutos = new JTable();
		produtoVendaBO = new ProdutoVendaBO();
	}
	
	public void abrirJanela(String cod, String produto){
		
		definicoesPagina();
		carregarGrid(cod, produto);
		setVisible(true);
		
	}
	
	public void definicoesPagina(){
		
		pnlCentro.setLayout(null);
		
		pnlPesquisa.setLayout(null);
		pnlPesquisa.setBounds(0,0,700,200);
		pnlPesquisa.setBackground(Color.BLACK);
		
		modeloTabProdutos = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		modeloTabProdutos.setColumnIdentifiers(new String[] {

		"Código", "Nome", "Valor de venda"

		});
		
		tabelaProdutos.getTableHeader().setReorderingAllowed(false);
		
		tabelaProdutos.setModel(modeloTabProdutos);

		barraTabProdutos = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabProdutos.setViewportView(tabelaProdutos);

		barraTabProdutos.setBounds(10, 220, pnlPesquisa.getWidth() - 20, 300);
		
		pnlCentro.add(pnlPesquisa);
		pnlCentro.add(barraTabProdutos);
		
		this.setLayout(new BorderLayout());		
		this.add(pnlCentro, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(700, 600);
		this.setModal(true);
		this.setLocationRelativeTo(null);
	}
	
	public void carregarGrid(String cod, String produto){
		
		List<ProdutoVendaVO> listaProdutos = produtoVendaBO.filtarPorNomeECodigo(cod, produto);
		
		String[] registro = new String[3];
		
		for (ProdutoVendaVO produtoVenda : listaProdutos) {
			
			registro[0] = produtoVenda.getCodProduto();
			registro[1] = produtoVenda.getDescricao();
			registro[2] = String.valueOf(produtoVenda.getPrecoVenda());
			
			modeloTabProdutos.addRow(registro);
		}
		
	}
	
}
