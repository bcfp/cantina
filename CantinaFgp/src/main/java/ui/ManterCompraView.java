package ui;

import interfaces.ITelaConsultar;

import java.awt.Color;
import java.awt.Component;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.CompraVO;
import vo.ItemCompraVO;
import vo.StatusVO;

public class ManterCompraView extends ManterDialogView<CompraVO> {
	
	private JComboBox<StatusVO> cbxStatus;
	
	private JTextField txtCodOc;
	private JTextField txtCodProdCompra;
	private JTextField txtProdCompra;
	private JTextField txtQtdeProdCompra;

	private JLabel lblStatus;
	private JLabel lblCodOc;
	private JLabel lblCodProdCompra;
	private JLabel lblProdCompra;
	private JLabel lblQtdeProdCompra;
	private JLabel lblProduto;
	
	private JButton btnBuscarProd;
	private JButton btnAddBuscarProd;
	
	private JPanel pnlCampos;
	private JTable tabItemCompra;
	private DefaultTableModel modeloTabItemCompra;
	private JScrollPane barraTabItemCompra;

	private Component lblValorProdCompra;

	private Component txtValorProdCompra;
	
	{
		
		int widthCampos = this.getWidth();

		int espXLbl = 20;
		int espXTxt = 110;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;

		pnlCampos = new JPanel();
		pnlCampos.setBounds(10, 10, widthCampos-25, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);

		cbxStatus = new JComboBox<StatusVO>();

		lblStatus = new JLabel("Status");
		lblCodOc = new JLabel("Número");
		lblCodProdCompra = new JLabel("Código");
		lblProdCompra = new JLabel("Produto");
		lblQtdeProdCompra = new JLabel("Quantidade");
		lblValorProdCompra = new JLabel("Valor");
		lblProduto = new JLabel("PRODUTO");

		txtCodOc = new JTextField();
		txtCodOc.setEnabled(false);
		txtCodProdCompra = new JTextField();
		txtProdCompra = new JTextField();
		txtQtdeProdCompra = new JTextField();
		txtValorProdCompra = new JTextField();

		cbxStatus.setBounds(espXLbl + 250, espY, 120, altura);

		lblStatus.setBounds(espXLbl + 200, espY, 50, altura);
		lblCodOc.setBounds(espXLbl, espY, 50, altura);
		lblProduto.setBounds(espXLbl, espY + espEntre, 80, altura);
		lblCodProdCompra.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblProdCompra.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblQtdeProdCompra.setBounds(espXLbl, espY + espEntre * 4, 80, altura);
		lblValorProdCompra.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtCodOc.setBounds(espXTxt, espY, 70, altura);
		txtCodProdCompra.setBounds(espXTxt, espY + espEntre * 2, 70, altura);
		txtProdCompra.setBounds(espXTxt, espY + espEntre * 3, 220, altura);
		txtQtdeProdCompra.setBounds(espXTxt, espY + espEntre * 4, 70, altura);
		txtValorProdCompra.setBounds(espXTxt, espY + espEntre * 5, 70, altura);
		
		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.setBounds(190, espY + espEntre * 2, 100, altura);
		
		btnAddBuscarProd = new JButton("+");
		btnAddBuscarProd.setBounds(190, espY + espEntre * 5, 50, altura);

		tabItemCompra = new JTable();
		modeloTabItemCompra = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modeloTabItemCompra.setColumnIdentifiers(new String[] {

		"Código", "Produto", "Quantidade", "Valor", "Total"

		});

		tabItemCompra.setModel(modeloTabItemCompra);

		barraTabItemCompra = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabItemCompra.setViewportView(tabItemCompra);

		barraTabItemCompra.setBounds(10, 240, pnlCampos.getWidth() - 20, 230);

		pnlCampos.add(barraTabItemCompra);
		pnlCampos.add(cbxStatus);
		pnlCampos.add(lblStatus);
		pnlCampos.add(lblCodOc);
		pnlCampos.add(lblProduto);
		pnlCampos.add(lblCodProdCompra);
		pnlCampos.add(lblProdCompra);
		pnlCampos.add(lblQtdeProdCompra);
		pnlCampos.add(lblValorProdCompra);
		pnlCampos.add(txtCodOc);
		pnlCampos.add(txtCodProdCompra);
		pnlCampos.add(txtProdCompra);
		pnlCampos.add(txtQtdeProdCompra);
		pnlCampos.add(txtValorProdCompra);
		pnlCampos.add(btnBuscarProd);
		pnlCampos.add(btnAddBuscarProd);

		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		
		incluirComponenteCentro(pnlCampos);

	}
	
	protected ManterCompraView(String tituloCabecalho) {
		super(tituloCabecalho);
	}
	
	protected ManterCompraView(String tituloCabecalho, CompraVO compra) {
		super(tituloCabecalho);
		carregarGridItens(compra.getItensCompra());
	}
	
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela(CompraVO objeto) {

		this.setVisible(true);
		
	}

	@Override
	public boolean cadastrar(CompraVO compra) {
			
		return false;
		
	}

	@Override
	protected boolean gravar() {
		JOptionPane.showMessageDialog(null, "Compra Salva");
		return false;
	}

	@Override
	protected void limparCampos() {
		
	}
	
	private void carregarGridItens(List<ItemCompraVO> itensCompra) {
		
		modeloTabItemCompra.setNumRows(0);
		
		Iterator<ItemCompraVO> iIc = itensCompra.iterator();
		
		while(iIc.hasNext()){
			
			ItemCompraVO ic = (ItemCompraVO) iIc.next();
			
			String[] registro = new String[5];

			registro[0] = ic.getProduto().getCodProduto();
			registro[1] = ic.getProduto().getDescricao();
			registro[2] = ic.getQtde().toString();
			registro[3] = ic.getValor().toString();
			Double total = ic.getQtde() * ic.getValor();
			registro[4] = total.toString();
			
			modeloTabItemCompra.addRow(registro);	
			
		}
				
	}

	@Override
	protected boolean alterar() {

		JOptionPane.showMessageDialog(null, "Compra Alterada");
		return false;
	
	}	

}
