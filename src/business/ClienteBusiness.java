package business;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import model.Cliente;

public class ClienteBusiness {	
	ClienteDao clienteDao = new ClienteDao(); 
	
	public void InserirCliente(Cliente cliente) {		
		if ((cliente != null) && (cliente.getNome().length() > 0)) {			
			clienteDao.insCliente(cliente);
		}
	}
	
	public void AtualizarCliente(Cliente cliente) {
		if (cliente != null) {			
			clienteDao.atlCliente(cliente);
		}
	}
	
	public void DeletarCliente(int idcliente){
		if (idcliente > 0) {	
			clienteDao.delCliente(idcliente);
		}
	}
		
	public List<Cliente> ListarCliente() {		
		return clienteDao.lstCliente();
	}
	
	public ArrayList<Object[]> TabelaCliente() {
		return clienteDao.tblCliente();
	}
	
	public ArrayList<Object[]> GridCliente() {
		return clienteDao.grdCliente();
	}
	
	public Cliente SetarCliente(int idcliente) {
		return idcliente > 0 ? clienteDao.setCliente(idcliente) : null; 		
	}

}
