package business;

import java.util.ArrayList;
import java.util.List;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoBusiness {
	ProdutoDao produtoDao = new ProdutoDao(); 
	
	public void InserirProduto(Produto produto) {		
		if ((produto != null) && (produto.getProduto().length() > 0)) {			
			produtoDao.insProduto(produto);		
		}
	}
	
	public void AtualizarProduto(Produto produto) {
		if (produto != null) {			
			produtoDao.atlProduto(produto);
		}
	}
	
	public void DeletarProduto(int idproduto){
		if (idproduto > 0) {	
			produtoDao.delProduto(idproduto);				
		}
	}
	
	public List<Produto> ListarProduto() {		
		return produtoDao.lstProduto();
	}

	public ArrayList<Object[]> TabelaProduto() {
		return produtoDao.tblProduto();
	}
	
	public ArrayList<Object[]> GridProduto() {
		return produtoDao.grdProduto();
	}
	
	public Produto SetarProduto(int idproduto) {
		return idproduto > 0 ? produtoDao.setProduto(idproduto) : null;
	}

}
