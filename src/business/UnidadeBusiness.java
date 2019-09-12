package business;

import java.util.List;

import dao.UnidadeDao;
import model.Unidade;

public class UnidadeBusiness {
	
	UnidadeDao unidadeDao = new UnidadeDao(); 
	
	public List<Unidade> ListarUnidade() {		
		return unidadeDao.lstUnidade();
	}
	
	public String[] ColunaUnidade(int idx) {
		return unidadeDao.colUnidade(idx);
	}

}
