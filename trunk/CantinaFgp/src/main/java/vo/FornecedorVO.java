package vo;

public class FornecedorVO extends GenericVO {

	private String nome;
	private String contato;
	private Boolean ativo;
	
	public FornecedorVO() {

	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected String getContato() {
		return contato;
	}

	protected void setContato(String contato) {
		this.contato = contato;
	}

	protected Boolean getAtivo() {
		return ativo;
	}

	protected void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
