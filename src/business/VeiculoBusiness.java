package business;

import java.util.ArrayList;
import java.util.List;

import dao.VeiculoDao;
import model.Veiculo;

public class VeiculoBusiness {
	VeiculoDao veiculoDao = new VeiculoDao(); 
	
	public void InserirVeiculo(Veiculo veiculo) {		
		if ((veiculo != null) && (veiculo.getPlaca().length() > 0)) {			
			veiculoDao.insVeiculo(veiculo);
		}
	}
	
	public void AtualizarVeiculo(Veiculo veiculo) {		
		if (veiculo != null) {	
			veiculoDao.atlVeiculo(veiculo);
		}
	}
	
	public void DeletarVeiculo(int idveiculo){
		if (idveiculo > 0) {	
			veiculoDao.delVeiculo(idveiculo);				
		}
	}	
	
	public List<Veiculo> ListarVeiculo() {		
		return veiculoDao.lstVeiculo();
	}
	
	public ArrayList<Object[]> TabelaVeiculo() {
		return veiculoDao.tblVeiculo();
	}
	
	public ArrayList<Object[]> GridVeiculo() {
		return veiculoDao.grdVeiculo();
	}
	
	public Veiculo SetarVeiculo(int idveiculo) {
		return idveiculo > 0 ? veiculoDao.setVeiculo(idveiculo) : null;		
	}

}
