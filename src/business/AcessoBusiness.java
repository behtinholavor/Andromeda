package business;

import java.util.List;

import dao.AcessoDao;
import model.Acesso;

public class AcessoBusiness {
	
	AcessoDao acessoDao = new AcessoDao();
	
	public List<Acesso> ListarAcesso() {		
		return acessoDao.lstAcesso();
	}
	
	public String[] ColunaAcesso(int idx) {
		return acessoDao.colAcesso(idx);
	}

}
