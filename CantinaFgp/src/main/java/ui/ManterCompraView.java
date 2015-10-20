package ui;

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

import org.jdesktop.swingx.JXDatePicker;

import vo.CompraVO;
import vo.ItemCompraVO;
import vo.StatusVO;
import enumeradores.TipoSolicitacao;

public class ManterCompraView extends ManterDialogView<CompraVO> {
	
	// Atributos Tela
	
	private JComboBox<String> cbxStatusCompra;
	private JComboBox<String> cbxFormaPgto;
	
	private JXDatePicker dtpDataCompra;
	
	private JTextField txtCodOc;
	private JTextField txtCodProdCompra;
	private JTextField txtProdCompra;
	private JTextField txtQtdeProdCompra;
	private JTextField txtValorProdCompra;
	private JTextField txtCodFornCompra;
	private JTextField txtFornCompra;
	
	private JLabel lblFormaPgto;
	private JLabel lblStatusCompra;
	private JLabel lblDataCompra;
	private JLabel lblCodOc;
	private JLabel lblCodProdCompra;
	private JLabel lblProdCompra;
	private JLabel lblQtdeProdCompra;
	private JLabel lblTitProduto;
	private JLabel lblValorProdCompra;
	private JLabel lblTitFornecedor;
	private JLabel lblCodFornCompra;
	private JLabel lblFornCompra;
	
	private JButton btnBuscarProd;
	private JButton btnBuscarForn;
	private JButton btnAddBuscarProd;
	
	private JPanel pnlCampos;
	private JTable tabItemCompra;
	private DefaultTableModel modeloTabItemCompra;
	private JScrollPane barraTabItemCompra;

	
	{
		
		int widthCampos = this.getWidth();

		int espXLbl = 20;
		int espXTxt = 110;
		int	espXForn = 350;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;

		pnlCampos = new JPanel();
		pnlCampos.setBounds(10, 10, widthCampos-25, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);

		dtpDataCompra = new JXDatePicker();
		
		cbxStatusCompra = new JComboBox<String>();
		cbxFormaPgto = new JComboBox<String>();

		lblFormaPgto = new JLabel("Pagamento");
		lblStatusCompra = new JLabel("Status");
		lblDataCompra = new JLabel("Data");
		lblCodOc = new JLabel("Número");
		lblCodProdCompra = new JLabel("Código");
		lblProdCompra = new JLabel("Produto");
		lblQtdeProdCompra = new JLabel("Quantidade");
		lblValorProdCompra = new JLabel("Valor");
		lblTitProduto = new JLabel("PRODUTO");
		lblTitFornecedor = new JLabel("FORNECEDOR");
		lblCodFornCompra = new JLabel("Código");
		lblFornCompra = new JLabel("Fornecedor");
		
		txtCodOc = new JTextField();
		txtCodOc.setEnabled(false);
		txtCodProdCompra = new JTextField();
		txtProdCompra = new JTextField();
		txtQtdeProdCompra = new JTextField();
		txtValorProdCompra = new JTextField();
		txtCodFornCompra = new JTextField();
		txtFornCompra = new JTextField();
		txtFornCompra.setEnabled(false);

		btnBuscarForn = new JButton("Consultar");
		btnBuscarProd = new JButton("Consultar");
		
		lblCodOc.setBounds(espXLbl, espY, 50, altura);
		lblTitProduto.setBounds(espXLbl, espY + espEntre, 80, altura);
		lblCodProdCompra.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblProdCompra.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblQtdeProdCompra.setBounds(espXLbl, espY + espEntre * 4, 80, altura);
		lblValorProdCompra.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtCodOc.setBounds(espXTxt, espY, 70, altura);
		txtCodProdCompra.setBounds(espXTxt, espY + espEntre * 2, 70, altura);
		txtProdCompra.setBounds(espXTxt, espY + espEntre * 3, 220, altura);
		txtQtdeProdCompra.setBounds(espXTxt, espY + espEntre * 4, 70, altura);
		txtValorProdCompra.setBounds(espXTxt, espY + espEntre * 5, 70, altura);
		
		btnBuscarProd.setBounds(espXTxt + 80, espY + espEntre * 2, 100, altura);
		
		lblStatusCompra.setBounds(480, espY, 80, altura);
		cbxStatusCompra.setBounds(530, espY, 130, altura);
		
		lblDataCompra.setBounds(250, espY, 80, altura);
		dtpDataCompra.setBounds(290, espY, 140, altura);
		
		lblTitFornecedor.setBounds(espXLbl + espXForn, espY + espEntre, 80, altura);
		lblCodFornCompra.setBounds(espXLbl + espXForn, espY + espEntre * 2, 80, altura);
		lblFornCompra.setBounds(espXLbl + espXForn, espY + espEntre * 3, 80, altura);
		
		txtCodFornCompra.setBounds(espXTxt + espXForn, espY + espEntre * 2, 70, altura);
		txtFornCompra.setBounds(espXTxt + espXForn, espY + espEntre * 3, 200, altura);

		btnBuscarForn.setBounds(espXTxt + espXForn + 80, espY + espEntre * 2, 100, altura);
		
		lblFormaPgto.setBounds(espXLbl + espXForn, espY + espEntre * 5, 80, altura);
		cbxFormaPgto.setBounds(espXTxt + espXForn, espY + espEntre * 5, 120, altura);
		
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
		pnlCampos.add(cbxStatusCompra);
		pnlCampos.add(dtpDataCompra);
		pnlCampos.add(cbxFormaPgto);
		pnlCampos.add(lblFormaPgto);
		pnlCampos.add(lblStatusCompra);
		pnlCampos.add(lblDataCompra);
		pnlCampos.add(lblCodOc);
		pnlCampos.add(lblTitProduto);
		pnlCampos.add(lblTitFornecedor);
		pnlCampos.add(lblCodFornCompra);
		pnlCampos.add(lblFornCompra);
		pnlCampos.add(txtFornCompra);
		pnlCampos.add(lblCodProdCompra);
		pnlCampos.add(lblProdCompra);
		pnlCampos.add(lblQtdeProdCompra);
		pnlCampos.add(lblValorProdCompra);
		pnlCampos.add(txtCodFornCompra);
		pnlCampos.add(txtCodOc);
		pnlCampos.add(txtCodProdCompra);
		pnlCampos.add(txtProdCompra);
		pnlCampos.add(txtQtdeProdCompra);
		pnlCampos.add(txtValorProdCompra);
		pnlCampos.add(btnBuscarProd);
		pnlCampos.add(btnBuscarForn);
		pnlCampos.add(btnAddBuscarProd);

		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		
		incluirComponenteCentro(pnlCampos);

	}
	
	protected ManterCompraView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
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
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Compra incluída");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Compra alterada");		
		return true;
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
	protected boolean habilitarCampos() {
		return false;
	}	

}
