package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class PrincipalView extends TelaView implements ComponentListener{
	
	private JMenuBar barraMenu;
	private JMenu menuConsulta;
	private JMenuItem subConsultaVendas;
	private JMenuItem subConsultaCompras;
	private JMenuItem subConsultaProdutos;
	private JMenuItem subConsultaClientes;
	private JMenuItem subConsultaFuncionarios;
	private JMenuItem subConsultaCantinas;
	private JMenu menuCadastrar;
	private JMenuItem subCadastrarVenda;
	private JMenuItem subCadastrarCompra;
	private JMenuItem subCadastrarProduto;
	private JMenuItem subCadastrarCliente;
	private JMenuItem subCadastrarFuncionario;
	private JMenuItem subCadastrarCantina;
	private JMenu menuOrdemProducao;
	private JMenuItem subConsultarOrdemProducao;
	private JMenuItem subCadastrarOrdemProducao;
	private JMenu menuEstoque;
	private ConsultarPanelView panelConsultar;

	// BLOCO DE INICIALIZAÇÃO
	{
		
		abrirJanela();
		
	}


	// MÉTODOS
	
	
	public void abrirJanela() {
		
		barraMenu = new JMenuBar();
		menuConsulta = new JMenu();
		
		subConsultaVendas = new JMenuItem();
		subConsultaVendas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.SHIFT_MASK));
		
		subConsultaCompras = new JMenuItem();
		subConsultaCompras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK));
		
		subConsultaProdutos = new JMenuItem();
		subConsultaProdutos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK));
		
		subConsultaClientes = new JMenuItem();
		subConsultaClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.SHIFT_MASK));
		
		subConsultaFuncionarios = new JMenuItem();
		subConsultaFuncionarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.SHIFT_MASK));
		
		subConsultaCantinas = new JMenuItem();
		subConsultaCantinas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK));
		
		menuCadastrar = new JMenu();
		subCadastrarVenda = new JMenuItem();
		subCadastrarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		
		subCadastrarCompra = new JMenuItem();
		subCadastrarCompra.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		subCadastrarProduto = new JMenuItem();
		subCadastrarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
		subCadastrarCliente = new JMenuItem();
		subCadastrarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		
		subCadastrarFuncionario = new JMenuItem();
		subCadastrarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		
		subCadastrarCantina = new JMenuItem();
		subCadastrarCantina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		menuOrdemProducao = new JMenu();
		subConsultarOrdemProducao = new JMenuItem();
		subConsultarOrdemProducao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
		subCadastrarOrdemProducao = new JMenuItem();
		subCadastrarOrdemProducao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		menuEstoque = new JMenu();
		
		atribuirTextos();
		
		menuConsulta.add(subConsultaCantinas);
		menuConsulta.add(subConsultaVendas);
		menuConsulta.add(subConsultaCompras);
		menuConsulta.add(subConsultaProdutos);
		menuConsulta.add(subConsultaClientes);
		menuConsulta.add(subConsultaFuncionarios);
		menuConsulta.add(subConsultaCantinas);
		
		menuCadastrar.add(subCadastrarVenda);
		menuCadastrar.add(subCadastrarCompra);
		menuCadastrar.add(subCadastrarProduto);
		menuCadastrar.add(subCadastrarCliente);
		menuCadastrar.add(subCadastrarFuncionario);
		menuCadastrar.add(subCadastrarCantina);

		menuOrdemProducao.add(subConsultarOrdemProducao);
		menuOrdemProducao.add(subCadastrarOrdemProducao);

		barraMenu.add(menuConsulta);
		barraMenu.add(menuCadastrar);
		barraMenu.add(menuOrdemProducao);
		barraMenu.add(menuEstoque);
		
		this.definicoesPagina();
		this.acoesBotoes();
		
	}
	
	private void acoesBotoes(){
		
		subConsultaProdutos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				PrincipalView.this.panelConsultar = new ConsultarProdutoView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
				
			}
			
			
		});
		
		subConsultaVendas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrincipalView.this.panelConsultar = new ConsultarVendaView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
				
			}
		});
		
		subConsultaCompras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrincipalView.this.panelConsultar = new ConsultarCompraView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
				
			}
		});
		
		subConsultaClientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrincipalView.this.panelConsultar = new ConsultarClienteView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
				
			}
		});
		
		subConsultaFuncionarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrincipalView.this.panelConsultar = new ConsultarFuncionarioView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
				
			}
		});
		
		subConsultaCantinas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrincipalView.this.panelConsultar = new ConsultarCantinaView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
				
			}
		});
		
		subConsultarOrdemProducao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrincipalView.this.panelConsultar = new ConsultarOrdemProducaoView();
				
				PrincipalView.this.abrirPanelConsulta(panelConsultar);
					
			}
		});
		
		subCadastrarOrdemProducao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterOrdemProducao();
				
			}
		});
		
		subCadastrarProduto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterProdutoView();
				
			}
		});
		
		subCadastrarCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterCompraView();
				
			}
			
		});

		
	}		

	
	private void abrirPanelConsulta(ConsultarPanelView panelConsultar){
		
		this.getContentPane().removeAll();
		this.getContentPane().setVisible(false);
		this.getContentPane().setVisible(true);
						
		this.centralizarPanel(panelConsultar);
		
		this.getContentPane().add(panelConsultar);
		
		this.validate();
		
	}
	
	private void centralizarPanel(ConsultarPanelView panel){
		
        int pontoLargura = ((this.getWidth() / 2) - (panel.getWidth() / 2) - 10); 
                
        panel.setLocation(pontoLargura, 0);  
        
	}
	
	protected void atribuirTextos() {

		menuConsulta.setText("Consultar");
		subConsultaVendas.setText("Vendas");
		subConsultaCompras.setText("Compras");
		subConsultaProdutos.setText("Produtos");
		subConsultaClientes.setText("Clientes");
		subConsultaFuncionarios.setText("Funcionários");
		subConsultaCantinas.setText("Cantinas");
		menuCadastrar.setText("Cadastrar");
		subCadastrarVenda.setText("Venda");
		subCadastrarCompra.setText("Compra");
		subCadastrarProduto.setText("Produto");
		subCadastrarCliente.setText("Cliente");
		subCadastrarFuncionario.setText("Funcionário");
		subCadastrarCantina.setText("Cantina");
		menuOrdemProducao.setText("Ordem de Produção");
		subConsultarOrdemProducao.setText("Consultar");
		subCadastrarOrdemProducao.setText("Novo");
		menuEstoque.setText("Estoque");
		
	}

	
	protected void definicoesPagina() {

		this.setLayout(null);
		this.setJMenuBar(barraMenu);
		this.setTitle("Cantina FGP");
		this.setExtendedState(PrincipalView.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800, 600));
		this.addComponentListener(this); 
		this.setVisible(true);
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		
		if(panelConsultar != null){

			this.centralizarPanel(panelConsultar);  
				
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}
	
	
}
