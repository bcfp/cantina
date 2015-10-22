package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.ProdutoVendaVO;
import bo.ProdutoVendaBO;

public class ConsultarProdutosDialogView extends JDialog{
	
	private JPanel pnlCentro;
	private JPanel pnlPesquisa;
	private JTable tabelaProdutos;
	private DefaultTableModel modeloTabProdutos;
	private JScrollPane barraTabProdutos;
	private JLabel lblNomeProduto;
	private JLabel lblCodProduto;
	
	private JTextField txtNomeProduto;
	private JTextField txtCodProduto;
	
	private JButton btnPesquisar;
	
	
	private ProdutoVendaBO produtoVendaBO;
	
	{
		pnlCentro = new JPanel();
		pnlPesquisa = new JPanel();
		tabelaProdutos = new JTable();
		produtoVendaBO = new ProdutoVendaBO();
		lblNomeProduto = new JLabel();
		lblCodProduto = new JLabel();
		txtNomeProduto = new JTextField();
		txtCodProduto = new JTextField();
		btnPesquisar = new JButton();

	}
	
	public void abrirJanela(String cod, String produto){
		
		definicoesPagina();
		txtNomeProduto.setText(produto);
		txtCodProduto.setText(cod);
//		
//		if(cod.trim() != "" || produto.trim() != ""){
//			new ActionListener() {
//				
//				@Override
//			public void actionPerformed(ActionEvent e) {
//				List<ProdutoVendaVO> listaProdutos = produtoVendaBO.filtarPorNomeECodigo(txtNomeProduto.getText(), txtCodProduto.getText());	
//				carregarGrid(listaProdutos);	
//				
//				
//				}
//			};
//			
//		}
//TODO continuar daqui CAINÃ
		btnPesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				List<ProdutoVendaVO> listaProdutos = produtoVendaBO.filtarPorNomeECodigo(txtNomeProduto.getText(), txtCodProduto.getText());
				//List<ProdutoVendaVO> listaProdutos = produtoVendaBO.consultarTodosProdutos();
				carregarGrid(listaProdutos);
				
			}
		});
		setVisible(true);
		
	}
	
	public void definicoesPagina(){
		
		pnlCentro.setLayout(null);
		
		pnlPesquisa.setLayout(null);
		pnlPesquisa.setBounds(0,0,700,200);
		pnlPesquisa.setBackground(Color.BLACK);
		
		modeloTabProdutos = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modeloTabProdutos.setColumnIdentifiers(new String[] {

		"Código", "Nome", "Valor de venda"

		});
		
		tabelaProdutos.setModel(modeloTabProdutos);

		barraTabProdutos = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabProdutos.setViewportView(tabelaProdutos);

		barraTabProdutos.setBounds(10, 220, pnlPesquisa.getWidth() - 20, 300);
		
		lblNomeProduto.setText("Nome:");
		lblNomeProduto.setBounds(50, 100, 100,30);
		
		txtNomeProduto.setBounds(100,100, 150, 30);
		
		lblCodProduto.setText("Código:");
		lblCodProduto.setBounds(260,100, 100, 30);
		
		txtCodProduto.setBounds(330,100, 100, 30);
		
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setBounds(440, 100, 100, 30);
		
		
		pnlCentro.add(lblNomeProduto);
		pnlCentro.add(txtNomeProduto);
		pnlCentro.add(lblCodProduto);
		pnlCentro.add(txtCodProduto);
		pnlCentro.add(btnPesquisar);
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
	
	public void carregarGrid(List<ProdutoVendaVO> listaProdutos){
		
		String[] registro = new String[3];
		
		for (ProdutoVendaVO produtoVenda : listaProdutos) {
			
			registro[0] = produtoVenda.getCodProduto();
			registro[1] = produtoVenda.getDescricao();
			registro[2] = String.valueOf(produtoVenda.getPrecoVenda());
			
			modeloTabProdutos.addRow(registro);
		}
		
	}
	
}
