package ui;

import interfaces.ITelaConsultar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import vo.MateriaPrimaVO;
import vo.OrdemProducaoVO;
import vo.StatusVO;

public class ManterOrdemProducao extends ManterDialogView<OrdemProducaoVO> {

	private JComboBox<StatusVO> cbxStatus;
	
	private JTextField txtCodOp;
	private JTextField txtCodProdVenda;
	private JTextField txtProdVenda;
	private JTextField txtQtdeProdVenda;

	private JLabel lblStatus;
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
	
	private List<ItemCompraVO> itensCompra; // será utilizado para compra de matérias primas
	
	{

		int widthCampos = this.getWidth() - 110;

		int espXLbl = 20;
		int espXTxt = 110;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;

		pnlCampos = new JPanel();
		pnlCampos.setBounds(10, 10, widthCampos, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);

		cbxStatus = new JComboBox<StatusVO>();

		lblStatus = new JLabel("Status");
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

		cbxStatus.setBounds(espXLbl + 250, espY, 120, altura);

		lblStatus.setBounds(espXLbl + 200, espY, 50, altura);
		lblCodOp.setBounds(espXLbl, espY, 50, altura);
		lblProduto.setBounds(espXLbl, espY + espEntre * 2, 80, altura);
		lblCodProdVenda.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblProdVenda.setBounds(espXLbl, espY + espEntre * 4, 50, altura);
		lblQtdeProdVenda.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtCodOp.setBounds(espXTxt, espY, 70, altura);
		txtCodProdVenda.setBounds(espXTxt, espY + espEntre * 3, 70, altura);
		txtProdVenda.setBounds(espXTxt, espY + espEntre * 4, 220, altura);
		txtQtdeProdVenda.setBounds(espXTxt, espY + espEntre * 5, 70, altura);

		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.setBounds(190, espY + espEntre * 3, 100, altura);

		tabMatPrimas = new JTable();
		modeloTabMatPrimas = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modeloTabMatPrimas.setColumnIdentifiers(new String[] {

		"Código", "Matéria-Prima", "Quantidade", "Estoque"

		});

		tabMatPrimas.setModel(modeloTabMatPrimas);

		barraTabMatPrimas = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabMatPrimas.setViewportView(tabMatPrimas);

		barraTabMatPrimas.setBounds(10, 240, pnlCampos.getWidth() - 20, 230);

		lblMatPrima.setBounds(espXLbl, 240, 100, altura);

		pnlCampos.add(cbxStatus);
		pnlCampos.add(barraTabMatPrimas);
		pnlCampos.add(lblStatus);
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
		//btnGerarOC.setEnabled(false);
		pnlBotoes.add(btnGerarOC);

		btnGerarOC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CompraVO c = new CompraVO();
				itensCompra = new ArrayList<ItemCompraVO>();
				ItemCompraVO i = new ItemCompraVO();
				MateriaPrimaVO m = new MateriaPrimaVO();
				m.setCodProduto("01");
				m.setDescricao("Mussarela");
				i.setProduto(m);
				i.setQtde(10D);
				i.setValor(5D);
				itensCompra.add(i);
				c.setItensCompra(itensCompra);
				
				new ManterCompraView("Gerar Compra", c).abrirJanela();

			}

		});

		this.add(pnlBotoes, BorderLayout.WEST);

	}
	
	public ManterOrdemProducao(String tituloCabecalho) {
		super(tituloCabecalho);
	}
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela(OrdemProducaoVO objeto) {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean cadastrar(OrdemProducaoVO ordemProducao) {
		
		return false;
		
	}

	@Override
	protected boolean gravar() {
		
		JOptionPane.showMessageDialog(null, "Ordem de Produção Salva");
		
		return true;
		
	}

	@Override
	protected boolean alterar() {

		JOptionPane.showMessageDialog(null, "OP Alterada");
		return false;
	
	}

	@Override
	protected void limparCampos() {
		
	}

}
