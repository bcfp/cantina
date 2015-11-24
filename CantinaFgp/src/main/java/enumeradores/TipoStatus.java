package enumeradores;

import java.util.List;

import vo.StatusVO;
import bo.StatusBO;

public enum TipoStatus {

	ORDEM_COMPRA("OC"), ORDEM_PRODUCAO("OP"), VENDA("OV");
	
	private StatusBO statusBo;
	private String tipo;
		
	TipoStatus(String tipo){
		statusBo = new StatusBO();
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
		
	public List<StatusVO> consultarTodosStatus() {
		return statusBo.consultarTodosStatus(this);
	}
	
}
