package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enumeradores.TipoProduto;
import vo.CantinaVO;
import vo.ClienteCantinaVO;
import vo.ClienteVO;
import vo.CompraVO;
import vo.EstoqueMateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.EstoqueProdutoVendaVO;
import vo.FormaPgtoVO;
import vo.FornecedorVO;
import vo.FuncionarioCantinaVO;
import vo.FuncionarioVO;
import vo.GenericVO;
import vo.ItemCompraVO;
import vo.LoteVO;
import vo.MateriaPrimaVO;
import vo.OrdemProducaoVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.StatusVO;
import vo.UnidadeProdutoVO;
import vo.VendaVO;

public class BancoFake {

	public static List<CompraVO> listaCompras;
	public static List<GenericVO> listaFornecedorGeneric;
	public static List<GenericVO> listaProdutosGeneric; 
	public static List<ProdutoVO> listaProdutos; 
	public static List<VendaVO> listaVendas; 
	public static List<ClienteVO> listaClientes;
	public static List<GenericVO> listaClientesGeneric;
	public static List<CantinaVO> listaCantinas;
	public static List<FuncionarioVO> listaFuncionarios;
	public static List<OrdemProducaoVO> listaOrdensProducao;
	public static List<ProdutoCantinaVO> listaEstoqueProduto;
	public static List<ItemCompraVO> listaItensCompra;
	public static List<ProdutoMateriaPrimaVO> receita;
	public static List<GenericVO> listaFuncCantinaGeneric;
	public static OrdemProducaoVO ordemProd;
	public static MateriaPrimaVO matPrima;
	public static MateriaPrimaVO matPrima2;
	public static ProdutoVendaVO prodVenda;
	public static ProdutoVendaVO prodVenda2;
	public static ProdutoVendaVO prodVenda3;
	public static ProdutoMateriaPrimaVO produtoMateriaPrima;
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
	public static FornecedorVO fornecedor;

	// BLOCO DE INICIALIZAÇÃO

	static {
		
		// fornecedor

		listaFornecedorGeneric = new ArrayList<GenericVO>();
		
		fornecedor = new FornecedorVO();
		fornecedor.setAtivo(true);
		fornecedor.setCodFornecedor("01");
		fornecedor.setNome("Fornecedor A");
		fornecedor.setContato("Contato Forn A");
		
		listaFornecedorGeneric.add(fornecedor);
		
		fornecedor = new FornecedorVO();
		fornecedor.setAtivo(true);
		fornecedor.setCodFornecedor("02");
		fornecedor.setNome("Fornecedor B");
		fornecedor.setContato("Contato Forn B");
		
		listaFornecedorGeneric.add(fornecedor);
		
		fornecedor = new FornecedorVO();
		fornecedor.setAtivo(true);
		fornecedor.setCodFornecedor("03");
		fornecedor.setNome("Fornecedor C");
		fornecedor.setContato("Contato Forn C");
		
		listaFornecedorGeneric.add(fornecedor);
		
		// cantina
		cantina = new CantinaVO();
		cantina.setIdCantina(1l);
		cantina.setNome("Cantina FGP");

		// Funcionario
		
		func = new FuncionarioVO();
		func.setCodPessoa("01");
		func.setNome("Fabiano");
		
		// FuncCantina
		
		funcCantina = new FuncionarioCantinaVO();
		funcCantina.setFuncionario(func);
		funcCantina.setCantina(cantina);
		
		listaFuncCantinaGeneric = new ArrayList<GenericVO>();
		
		listaFuncCantinaGeneric.add(funcCantina);
		
		
		// ordens producao
		
		listaOrdensProducao = new ArrayList<OrdemProducaoVO>();

		unidade = new UnidadeProdutoVO();
		unidade.setAbreviatura("UNID");
		unidade.setDescricao("unidade");
		
		prodVenda = new ProdutoVendaVO();
		prodVenda.setDescricao("Coxinha");
		prodVenda.setCodProduto("01");
		prodVenda.setDiasVencimento(3);
		prodVenda.setPrecoCusto(1D);
		prodVenda.setPrecoVenda(3D);
		prodVenda.setAtivo(true);
		prodVenda.setUnidade(unidade);
		prodVenda.setLote(true);
		prodVenda.setTipo(TipoProduto.PRODUCAO);
		
		prodVenda2 = new ProdutoVendaVO();
		prodVenda2.setDescricao("Esfiha Arabe");
		prodVenda2.setCodProduto("02");
		prodVenda2.setDiasVencimento(3);
		prodVenda2.setPrecoCusto(1.5D);
		prodVenda2.setPrecoVenda(3D);
		prodVenda2.setAtivo(true);
		prodVenda2.setUnidade(unidade);
		prodVenda2.setLote(true);
		prodVenda2.setTipo(TipoProduto.PRODUCAO);
		
		prodVenda3 = new ProdutoVendaVO();
		prodVenda3.setDescricao("Halls Cereja");
		prodVenda3.setCodProduto("03");
		prodVenda3.setPrecoCusto(0.7D);
		prodVenda3.setPrecoVenda(1.5D);
		prodVenda3.setAtivo(true);
		prodVenda3.setUnidade(unidade);
		prodVenda3.setLote(true);
		prodVenda3.setTipo(TipoProduto.REVENDA);
		
		
		matPrima = new MateriaPrimaVO();
		matPrima.setDescricao("Frango");
		matPrima.setCodProduto("04");
		matPrima.setLote(true);
		matPrima.setPrecoCusto(2D);
		matPrima.setAtivo(true);
		matPrima.setUnidade(unidade);
		matPrima.setTipo(TipoProduto.MATERIA_PRIMA);
		
		matPrima2 = new MateriaPrimaVO();
		matPrima2.setDescricao("Farinha");
		matPrima2.setCodProduto("05");
		matPrima2.setLote(true);
		matPrima2.setPrecoCusto(200D);
		matPrima2.setAtivo(true);
		matPrima2.setUnidade(unidade);
		matPrima2.setTipo(TipoProduto.MATERIA_PRIMA);
		
		receita = new ArrayList<ProdutoMateriaPrimaVO>();
		
		produtoMateriaPrima = new ProdutoMateriaPrimaVO();
		produtoMateriaPrima.setCodReceita("01");
		produtoMateriaPrima.setMateriaPrima(matPrima);
		produtoMateriaPrima.setQtde(0.3D);
		produtoMateriaPrima.setUnidade(unidade);

		
		receita.add(produtoMateriaPrima);
		
		produtoMateriaPrima = new ProdutoMateriaPrimaVO();
		produtoMateriaPrima.setCodReceita("02");
		produtoMateriaPrima.setMateriaPrima(matPrima2);
		produtoMateriaPrima.setQtde(300D);
		produtoMateriaPrima.setUnidade(unidade);
		
		receita.add(produtoMateriaPrima);
		
		prodVenda.setReceita(receita);
		prodVenda2.setReceita(receita);
		
		listaItensCompra = new ArrayList<ItemCompraVO>();
		
		ItemCompraVO ic = new ItemCompraVO();
		ic.setProduto(matPrima);
		ic.setQtde(2d);
		ic.setValor(5d);
		
		listaItensCompra.add(ic);
		
		ic = new ItemCompraVO();
		ic.setProduto(matPrima2);
		ic.setQtde(2d);
		ic.setValor(5d);
		
		listaItensCompra.add(ic);
		
		ic = new ItemCompraVO();
		ic.setProduto(prodVenda);
		ic.setQtde(2d);
		ic.setValor(5d);
		
		listaItensCompra.add(ic);
		listaItensCompra.add(ic);
		
		listaEstoqueProduto = new ArrayList<ProdutoCantinaVO>();
		
		estMatPrima = new EstoqueMateriaPrimaVO();
		estMatPrima.setCantina(cantina);
		estMatPrima.setProduto(matPrima);
		estMatPrima.setQtdeAtual(4D);
		estMatPrima.setQtdeMinima(5D);
		estMatPrima.setQtdeMaxima(15D);
		
		listaEstoqueProduto.add(estMatPrima);
		
		estMatPrima = new EstoqueMateriaPrimaVO();
		estMatPrima.setCantina(cantina);
		estMatPrima.setProduto(matPrima2);
		estMatPrima.setQtdeAtual(100D);
		estMatPrima.setQtdeMinima(1000D);
		estMatPrima.setQtdeMaxima(3000D);
		
		listaEstoqueProduto.add(estMatPrima);
		
		estProdVenda = new EstoqueProdutoVendaVO();
		estProdVenda.setCantina(cantina);
		estProdVenda.setProduto(prodVenda);
		estProdVenda.setQtdeAtual(9D);
		estProdVenda.setQtdeMinima(10D);
		estProdVenda.setQtdeMaxima(20D);
		
		listaEstoqueProduto.add(estProdVenda);
		
		estProdVenda = new EstoqueProdutoVendaVO();
		estProdVenda.setCantina(cantina);
		estProdVenda.setProduto(prodVenda2);
		estProdVenda.setQtdeAtual(9D);
		estProdVenda.setQtdeMinima(10D);
		estProdVenda.setQtdeMaxima(20D);

		listaEstoqueProduto.add(estProdVenda);
		
		estProdVenda = new EstoqueProdutoVendaVO();
		estProdVenda.setCantina(cantina);
		estProdVenda.setProduto(prodVenda3);
		estProdVenda.setQtdeAtual(5D);
		estProdVenda.setQtdeMinima(6D);
		estProdVenda.setQtdeMaxima(10D);

		listaEstoqueProduto.add(estProdVenda);
		
		
		
		
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
		listaClientesGeneric = new ArrayList<GenericVO>();
				
		cliente = new ClienteVO();
		
		cliente.setCodPessoa("01");
		cliente.setNome("Bruno Silva");

		listaClientes.add(cliente);
		listaClientesGeneric.add(cliente);
		
		cliente = new ClienteVO();
		
		cliente.setCodPessoa("02");
		cliente.setNome("Marcia Fernandes");

		listaClientes.add(cliente);
		listaClientesGeneric.add(cliente);

		listaProdutosGeneric = new ArrayList<GenericVO>();
		listaProdutos = new ArrayList<ProdutoVO>();

		listaProdutosGeneric.add(prodVenda);
		listaProdutosGeneric.add(prodVenda2);
		listaProdutos.add(prodVenda);
		listaProdutos.add(prodVenda2);
		listaProdutos.add(prodVenda3);

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
