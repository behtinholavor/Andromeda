package business;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import model.Usuario;

public class UsuarioBusiness {	
	UsuarioDao usuarioDao = new UsuarioDao(); 
	
	public void InserirUsuario(Usuario usuario) {		
		if ((usuario != null) && (usuario.getUsuario().length() > 0)) {			
			usuarioDao.insUsuario(usuario);
		}	
	}
	
	public void AtualizarUsuario(Usuario usuario) {
		if (usuario != null) {			
			usuarioDao.atlUsuario(usuario);
		}		
	}
	
	public void DeletarUsuario(int idusuario){
		if (idusuario > 0) {	
			usuarioDao.delUsuario(idusuario);				
		}
	}	
	
	public List<Usuario> ListarUsuario() {		
		return usuarioDao.lstUsuario();
	}		
	
	public Usuario AutenticarUsuario(String login, String senha) {						
		return usuarioDao.autUsuario(login, senha);				
	}
	
	public boolean ResetartUsuario(Usuario usuario) {
		if (usuario != null) {			
			usuarioDao.rstUsuario(usuario);
			return true;
		} else {		
			return false;
		}
	}
	
	public ArrayList<Object[]> TabelaUsuario() {
		return usuarioDao.tblUsuario();
	}
	
	public ArrayList<Object[]> GridUsuario() {
		return usuarioDao.grdUsuario();
	}

}
