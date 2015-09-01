package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	private JButton btnBuscarProd;
	
	private JPanel campos;
	
	public CadastrarOrdemProducao() {
		super("Nova Ordem de Produção");
		abrirJanela();
	}

	@Override
	public void abrirJanela() {

		int espXLbl = 10;
		int espXTxt = 100;
		int espY = 10;
		int espEntre = 35;
		int altura = 30;
		
		campos = new JPanel();
		campos.setBounds(10,10,400,400);
		campos.setLayout(null);
		campos.setBackground(Color.LIGHT_GRAY);
		
		lblCodOp = new JLabel("Número");
		lblCodProdVenda = new JLabel("Código");
		lblProdVenda = new JLabel("Produto");
		lblQtdeProdVenda = new JLabel("Quantidade");
		lblProduto = new JLabel("PRODUTO");
		
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
		txtProdVenda.setBounds(espXTxt, espY+espEntre*4, 70, altura);
		txtQtdeProdVenda.setBounds(espXTxt, espY+espEntre*5, 70, altura);
		
		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.setBounds(190, espY+espEntre*3, 100, altura);

		campos.add(lblCodOp);
		campos.add(lblProduto);
		campos.add(lblCodProdVenda);
		campos.add(lblProdVenda);
		campos.add(lblQtdeProdVenda);
		campos.add(txtCodOp);
		campos.add(txtCodProdVenda);
		campos.add(txtProdVenda);
		campos.add(txtQtdeProdVenda);
		campos.add(btnBuscarProd);
		
		incluirComponenteCentro(campos);

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
