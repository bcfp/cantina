package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vo.CantinaVO;
import vo.ClienteCantinaVO;
import vo.ClienteVO;
import vo.CompraVO;
import vo.EstoqueMateriaPrimaVO;
import vo.EstoqueProdutoVO;
import vo.EstoqueProdutoVendaVO;
import vo.FormaPgtoVO;
import vo.FuncionarioCantinaVO;
import vo.FuncionarioVO;
import vo.ItemCompraVO;
import vo.LoteVO;
import vo.MateriaPrimaVO;
import vo.OrdemProducaoVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import vo.ReceitaVO;
import vo.StatusVO;
import vo.UnidadeProdutoVO;
import vo.VendaVO;

public class BancoFake {
	
	public static List<CompraVO> listaCompras;
	public static List<ProdutoVO> listaProdutos; 
	public static List<VendaVO> listaVendas; 
	public static List<ClienteVO> listaClientes;
	public static List<CantinaVO> listaCantinas;
	public static List<FuncionarioVO> listaFuncionarios;
	public static List<OrdemProducaoVO> listaOrdensProducao;
	public static List<EstoqueProdutoVO> estoqueProdutoVO;
	public static OrdemProducaoVO ordemProd;
	public static MateriaPrimaVO matPrima;
	public static ProdutoVendaVO prodVenda;
	public static ReceitaVO receita;
	public static FuncionarioVO func;
	public static ClienteVO cliente;
	public static EstoqueMateriaPrimaVO estMatPrima;
	public static EstoqueProdutoVendaVO estProdVenda;
	public static LoteVO lote;
	public static CantinaVO cantina;
	public static CompraVO compra;
	public static ItemCompraVO itemCompra;
	public static ClienteCantinaVO clienteCant;
	public static FuncionarioCantinaVO funcCantina; 
	public static UnidadeProdutoVO unidade;
	public static FormaPgtoVO formaPgto;

	// BLOCO DE INICIALIZAÇÃO

	static {
		
		// cantina
		cantina = new CantinaVO();
		cantina.setIdCantina(1l);
		cantina.setNome("Cantina FGP");

		func = new FuncionarioVO();
		func.setCodPessoa("01");
		func.setNome("Fabiano");
		
		funcCantina = new FuncionarioCantinaVO();
		funcCantina.setFuncionario(func);
		funcCantina.setCantina(cantina);
		
		
		// ordens producao
		
		listaOrdensProducao = new ArrayList<OrdemProducaoVO>();

		unidade = new UnidadeProdutoVO();
		unidade.setAbreviatura("UNID");
		unidade.setDescricao("unidade");
		
		prodVenda = new ProdutoVendaVO();
		prodVenda.setDescricao("Coxinha");
		prodVenda.setDiasVencimento(3);
		prodVenda.setFabricado(true);
		prodVenda.setCodProduto("01");
		prodVenda.setPrecoCusto(1D);
		prodVenda.setPrecoVenda(3D);
		prodVenda.setStatus(true);
		prodVenda.setUnidade(unidade);
		prodVenda.setLote(true);
		
		matPrima = new MateriaPrimaVO();
		matPrima.setDescricao("Frango");
		matPrima.setLote(true);
		matPrima.setPrecoCusto(2D);
		matPrima.setStatus(true);
		matPrima.setUnidade(unidade);
		
		receita = new ReceitaVO();
		receita.setCodReceita("01");
		receita.setMateriaPrima(matPrima);
		receita.setProduto(prodVenda);
		receita.setQtde(0.3D);
		receita.setUnidade(unidade);
		
		estMatPrima = new EstoqueMateriaPrimaVO();
		estMatPrima.setCantina(cantina);
		estMatPrima.setProduto(matPrima);
		estMatPrima.setQtdeAtual(10D);
		estMatPrima.setQtdeMinima(5D);
		estMatPrima.setQtdeMaxima(15D);
		
		estProdVenda = new EstoqueProdutoVendaVO();
		estProdVenda.setCantina(cantina);
		estProdVenda.setProduto(matPrima);
		estProdVenda.setQtdeAtual(9D);
		estProdVenda.setQtdeMinima(10D);
		estProdVenda.setQtdeMaxima(20D);
		
		lote = new LoteVO();
		lote.setCodLote("01");
		lote.setDataVencimento(new Date());
		lote.setEstoqueProduto(estProdVenda);
		lote.setQtde(9D);
				
		ordemProd = new OrdemProducaoVO();
		
		ordemProd.setCodOrdemProducao("001");
		ordemProd.setData(new Date());
		ordemProd.setProdutoVenda(prodVenda);
		ordemProd.setQtde(20);
		StatusVO s = new StatusVO();
		s.setDescricao("Finalizado");
		ordemProd.setStatus(s);
		
		listaOrdensProducao.add(ordemProd);
		
		ordemProd = new OrdemProducaoVO();
		
		ordemProd.setCodOrdemProducao("002");
		ordemProd.setData(new Date());
		ordemProd.setProdutoVenda(prodVenda);
		ordemProd.setQtde(10);
		s = new StatusVO();
		s.setDescricao("Em produção");
		ordemProd.setStatus(s);
		
		listaOrdensProducao.add(ordemProd);
		
		
		listaFuncionarios = new ArrayList<FuncionarioVO>();
		
		func = new FuncionarioVO();
		
		func.setCodPessoa("01");
		func.setNome("Bruno Silva");
		
		listaFuncionarios.add(func);
		
		func = new FuncionarioVO();
		
		func.setCodPessoa("02");
		func.setNome("Cainã");
		
		listaFuncionarios.add(func);
		
		listaCompras = new ArrayList<CompraVO>();
		
		compra = new CompraVO();
		
		compra.setCodCompra("01");
		compra.setData(new Date());
		
		listaCompras.add(compra);
		
		compra = new CompraVO();
		
		compra.setCodCompra("02");
		compra.setData(new Date());
		
		listaCompras.add(compra);
		
		listaCantinas = new ArrayList<CantinaVO>();
		
		listaCantinas.add(cantina);
		
		cantina = new CantinaVO();
		
		cantina.setNome("Cantina Escola");
		cantina.setIdCantina(2L);

		listaCantinas.add(cantina);
		
		listaClientes = new ArrayList<ClienteVO>();
				
		cliente = new ClienteVO();
		
		cliente.setCodPessoa("01");
		cliente.setNome("Bruno Silva");
		
		listaClientes.add(cliente);
		
		cliente = new ClienteVO();
		
		cliente.setCodPessoa("02");
		cliente.setNome("Marcia Fernandes");
		
		listaClientes.add(cliente);
		
		listaProdutos = new ArrayList<ProdutoVO>();

		listaProdutos.add(prodVenda);

		// -----

		listaVendas = new ArrayList<VendaVO>();

		VendaVO venda = null;
		
		venda = new VendaVO();
		venda.setCodVenda("01");
		venda.setData(new Date());
		ClienteVO c = new ClienteVO();
		c.setNome("Bruno Silva");
		ClienteCantinaVO cc = new ClienteCantinaVO();
		cc.setCliente(c);
		venda.setClienteCantina(cc);

		listaVendas.add(venda);

		// -----
		
		venda = new VendaVO();
		venda.setCodVenda("02");
		venda.setData(new Date());
		c = new ClienteVO();
		c.setNome("Marcia Fernandes");
		cc = new ClienteCantinaVO();
		cc.setCliente(c);
		venda.setClienteCantina(cc);
		
		listaVendas.add(venda);

	}

}
