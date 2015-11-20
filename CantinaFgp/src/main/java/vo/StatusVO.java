package vo;

import enumeradores.TipoStatus;

public class StatusVO extends GenericVO{
	
	private Long idStatus;
	private String descricao;
	private TipoStatus tipoStatus;
	
	public StatusVO() {

	}

	@Override
	public String toString() {
		return descricao;
	}
	
	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoStatus getTipoStatus() {
		return tipoStatus;
	}

	public void setTipoStatus(TipoStatus tipoStatus) {
		this.tipoStatus = tipoStatus;
	}
	
}
