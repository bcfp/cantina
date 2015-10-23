package ui;

import interfaces.ITelaBuscar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.GenericVO;

public class BuscarDialogView extends JDialog{
	
	private JPanel pnlCentro;
	private JPanel pnlPesquisa;
	private JTable tabItens;
	private DefaultTableModel modeloTabItens;
	private JScrollPane barraTabItens;
	private JLabel lblNomeItem;
	private JLabel lblCodItem;
	
	private String[] titulosTab;
	
	private JTextField txtNomeItem;
	private JTextField txtCodItem;
	
	private JButton btnPesquisar;
	
	private List<GenericVO> listaItens;
		
	private ITelaBuscar telaBuscar;
	
	
	{
		pnlCentro = new JPanel();
		pnlPesquisa = new JPanel();
		tabItens = new JTable();
		lblNomeItem = new JLabel();
		lblCodItem = new JLabel();
		txtNomeItem = new JTextField();
		txtCodItem = new JTextField();
		btnPesquisar = new JButton();

	}
	
	public BuscarDialogView(ITelaBuscar telaBuscar, String[] titulosTab) {
		this.telaBuscar = telaBuscar;
		this.titulosTab = titulosTab;
	}
		
	public void abrirJanela(){
				
		definicoesPagina();

		btnPesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Map<String, String> parametros = new HashMap<String, String>();

				parametros.put(lblCodItem.getText(), txtCodItem.getText());
				parametros.put(lblNomeItem.getText(), txtNomeItem.getText());
				
				listaItens = telaBuscar.pesquisar(parametros);
				
				carregarGridItens(listaItens);
				
			} 
		});
		
		tabItens.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(e.getClickCount()==2){
					if(tabItens.getSelectedRow() != -1){

						telaBuscar.carregarItemSelecionado(listaItens.get(tabItens.getSelectedRow()));
						BuscarDialogView.this.dispose();
						
					}
				}
				
			}
		});
		
		setVisible(true);
		
	}

	private void carregarGridItens(List<GenericVO> listaItens) {
		
		modeloTabItens.setNumRows(0);
		
		if(listaItens != null){
			
			for (GenericVO item : listaItens) {
				modeloTabItens.addRow(telaBuscar.definirGridBusca(item));
			}

		}
		
	}
	
	
	public void definicoesPagina(){
		
		pnlCentro.setLayout(null);
		
		pnlPesquisa.setLayout(null);
		pnlPesquisa.setBounds(0,0,700,200);
		pnlPesquisa.setBackground(Color.BLACK);
		
		modeloTabItens = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modeloTabItens.setColumnIdentifiers(titulosTab);
		
		tabItens.setModel(modeloTabItens);

		barraTabItens = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabItens.setViewportView(tabItens);

		barraTabItens.setBounds(10, 220, pnlPesquisa.getWidth() - 20, 300);
		
		lblNomeItem.setText("Nome");
		lblNomeItem.setBounds(50, 100, 100,30);
		
		txtNomeItem.setBounds(100,100, 150, 30);
		
		lblCodItem.setText("Código");
		lblCodItem.setBounds(260,100, 100, 30);
		
		txtCodItem.setBounds(330,100, 100, 30);
		
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setBounds(440, 100, 100, 30);
				
		pnlCentro.add(lblNomeItem);
		pnlCentro.add(txtNomeItem);
		pnlCentro.add(lblCodItem);
		pnlCentro.add(txtCodItem);
		pnlCentro.add(btnPesquisar);
		pnlCentro.add(pnlPesquisa);
		pnlCentro.add(barraTabItens);
		
		this.setLayout(new BorderLayout());		
		this.add(pnlCentro, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(700, 600);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		
	}

}
