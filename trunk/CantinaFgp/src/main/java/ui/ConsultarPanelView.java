package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.GenericVO;

public abstract class ConsultarPanelView<T extends GenericVO> extends JPanel{
	
	private JTable tabGeneric;
	private DefaultTableModel modeloTabGeneric;
	private JScrollPane barraTabGeneric;
	private JButton btnFechar;
	private JButton btnNovo;
	
	public ConsultarPanelView(String[] titulos, List<T> listaGenerics, int espX, int espY, int larg, int alt) {
		botaoNovo();
		botaoFechar();
		tabGeneric(titulos, listaGenerics, espX, espY, larg, alt);
		this.add(barraTabGeneric);
	}
	
	private void tabGeneric(final String[] titulos, List<T> listaGenerics, int espX, int espY, int larg, int alt){
		
		tabGeneric = new JTable();
		modeloTabGeneric = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		barraTabGeneric = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		modeloTabGeneric.setColumnIdentifiers(titulos);
		
		tabGeneric.setModel(modeloTabGeneric);
		
		barraTabGeneric.setViewportView(tabGeneric);
		barraTabGeneric.setBounds(espX, espY, larg, alt);
		
		carregarGridItens(listaGenerics);
		mouseClickedTab();
	
	}
	
	protected abstract void mouseClickedTab();
	
	protected abstract void carregarGridItens(List<T> listaGenerics);

	
	private void botaoFechar(){
		
		btnFechar = new JButton("Fechar Tela");
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.setVisible(false);
				
			}
			
		});
		
		btnFechar.setVisible(true);
		btnFechar.setBounds(300, 10, 120, 40);
		add(btnFechar);
		
	}
	
	protected abstract void getTelaNovo();
		
	private void botaoNovo(){
		
		btnNovo = new JButton("Novo");
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.getTelaNovo();
				
			}
			
		});

		btnNovo.setBounds(10, 10, 120, 40);
		btnNovo.setVisible(true);
		add(btnNovo);
		
	}
		
	protected JTable getTabGeneric() {
		return tabGeneric;
	}

	protected DefaultTableModel getModeloTabGeneric() {
		return modeloTabGeneric;
	}

	
}
