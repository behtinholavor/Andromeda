package business;

import java.util.List;

import dao.MoedaDao;
import model.Moeda;

public class MoedaBusiness {

	MoedaDao moedaDao = new MoedaDao(); 
	
	public List<Moeda> ListarMoeda() {		
		return moedaDao.lstMoeda();
	}
	
	public String[] ColunaMoeda(int idx) {
		return moedaDao.colMoeda(idx);
	}

}
