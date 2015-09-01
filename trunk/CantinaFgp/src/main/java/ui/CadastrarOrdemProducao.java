package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.OrdemProducaoVO;

public class CadastrarOrdemProducao extends CadastrarDialogView<OrdemProducaoVO>{

	private JTextField txtCodOp;
	private JTextField txtCodProdVenda;
	private JTextField txtProdVenda;
	private JTextField txtQtdeProdVenda;

	private JLabel lblCodOp;
	private JLabel lblCodProdVenda;
	private JLabel lblProdVenda;
	private JLabel lblQtdeProdVenda;
	private JLabel lblProduto;
	private JLabel lblMatPrima;	
	
	private JButton btnGerarOC;
	private JButton btnBuscarProd;

	private JTable tabMatPrimas;
	private DefaultTableModel modeloTabMatPrimas;
	private JScrollPane barraTabMatPrimas;
	
	private JPanel pnlBotoes;
	private JPanel pnlCampos;
	
	
	public CadastrarOrdemProducao() {
		super("Nova Ordem de Produção");
		abrirJanela();
	}

	@Override
	public void abrirJanela() {
		
		int widthCampos = 690;

		int espXLbl = 20;
		int espXTxt = 110;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		pnlCampos = new JPanel();
		pnlCampos.setBounds(10,10,widthCampos,480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		
		lblCodOp = new JLabel("Número");
		lblCodProdVenda = new JLabel("Código");
		lblProdVenda = new JLabel("Produto");
		lblQtdeProdVenda = new JLabel("Quantidade");
		lblProduto = new JLabel("PRODUTO");
		lblMatPrima = new JLabel("RECEITA");
		
		txtCodOp = new JTextField();
		txtCodOp.setEnabled(false);
		txtCodProdVenda = new JTextField();
		txtProdVenda = new JTextField();
		txtQtdeProdVenda = new JTextField();

		lblCodOp.setBounds(espXLbl, espY, 50, altura);
		lblProduto.setBounds(espXLbl, espY+espEntre*2, 80, altura);
		lblCodProdVenda.setBounds(espXLbl, espY+espEntre*3, 50, altura);
		lblProdVenda.setBounds(espXLbl, espY+espEntre*4, 50, altura);
		lblQtdeProdVenda.setBounds(espXLbl, espY+espEntre*5, 80, altura);
		
		txtCodOp.setBounds(espXTxt, espY, 70, altura);
		txtCodProdVenda.setBounds(espXTxt, espY+espEntre*3, 70, altura);
		txtProdVenda.setBounds(espXTxt, espY+espEntre*4, 220, altura);
		txtQtdeProdVenda.setBounds(espXTxt, espY+espEntre*5, 70, altura);
		
		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.setBounds(190, espY+espEntre*3, 100, altura);
		
		tabMatPrimas = new JTable();
		modeloTabMatPrimas = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		modeloTabMatPrimas.setColumnIdentifiers(new String[] {

				"Código",
				"Matéria-Prima",
				"Quantidade",
				"Estoque"

		});
		
		tabMatPrimas.setModel(modeloTabMatPrimas);
		
		barraTabMatPrimas = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		barraTabMatPrimas.setViewportView(tabMatPrimas);
		
		barraTabMatPrimas.setBounds(10, 270, widthCampos-20, 200);
		
		lblMatPrima.setBounds(espXLbl, 240, 100, altura);
		
		
		pnlCampos.add(barraTabMatPrimas);
		pnlCampos.add(lblCodOp);
		pnlCampos.add(lblProduto);
		pnlCampos.add(lblCodProdVenda);
		pnlCampos.add(lblProdVenda);
		pnlCampos.add(lblQtdeProdVenda);
		pnlCampos.add(lblMatPrima);
		pnlCampos.add(txtCodOp);
		pnlCampos.add(txtCodProdVenda);
		pnlCampos.add(txtProdVenda);
		pnlCampos.add(txtQtdeProdVenda);
		pnlCampos.add(btnBuscarProd);
		
		incluirComponenteCentro(pnlCampos);
		

		
		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(new GridLayout(10, 1));
		pnlBotoes.setBackground(Color.WHITE);
		
		btnGerarOC = new JButton("Gerar OC");
		btnGerarOC.setBounds(10, 10, 100, 100);
		pnlBotoes.add(btnGerarOC);
		
		this.add(pnlBotoes, BorderLayout.WEST);
		

		definicoesPagina();
		
	}

	@Override
	public boolean cadastrar(OrdemProducaoVO ordemProducao) {
		
		return false;
		
	}

	@Override
	protected boolean botaoGravar() {
		
		JOptionPane.showMessageDialog(null, "Ordem de Produção Salva");
		
		return true;
		
	}
	
	private void definicoesPagina() {
		
		this.setSize(800, 600);
		this.setVisible(true);
		
	}

	@Override
	protected void limparCampos() {
		
	}
	
	

}
