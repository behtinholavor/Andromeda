package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.Conexao;
import util.Global;

public class ProdutoDao {

	public ProdutoDao() {
		
	}
	
	public void insProduto(Produto produto) {
		String sql = "INSERT INTO produtos("
				+ "produto, codproduto, idunidade, preco, descricao, uscadast, dtcadast, usmodifi, dtmodifi)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
		Connection conn = null;
		PreparedStatement pstm = null;
				
		try {							
			conn = (Connection) Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);			
			pstm.setString(1, produto.getProduto());
			pstm.setString(2, produto.getCodproduto());
			pstm.setInt(3, produto.getIdunidade());
			pstm.setFloat(4, produto.getPreco());
			pstm.setString(5, produto.getDescricao());			
			pstm.setString(6, produto.getUscadast());
			pstm.setTimestamp(7, produto.getDtcadast());			
			pstm.setString(8, null);
			pstm.setTimestamp(9, null);																	
			pstm.execute();				
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {
				
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		
	}
	
	public void delProduto(int idproduto) {
		String sql = "DELETE FROM produtos WHERE idproduto = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
				
		try {							
			conn = (Connection) Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);						
			pstm.setInt(1, idproduto);			
			pstm.execute();				
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	public void atlProduto(Produto produto) {	
		String sql = "UPDATE produtos SET "
				+ "produto=?, "
				+ "codproduto=?, "
				+ "idunidade=?, "
				+ "preco=?, "
				+ "descricao=?, "				
				+ "usmodifi=?, "
				+ "dtmodifi=? "
				+ "WHERE idproduto=?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
				
		try {							
			conn = (Connection) Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);			
			pstm.setString(1, produto.getProduto());
			pstm.setString(2, produto.getCodproduto());
			pstm.setInt(3, produto.getIdunidade());			
			pstm.setFloat(4, produto.getPreco());							
			pstm.setString(5, produto.getDescricao());										
			pstm.setString(6, produto.getUsmodifi());
			pstm.setTimestamp(7, produto.getDtmodifi());									
			pstm.setInt(8, produto.getIdproduto());	
			pstm.execute();				
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	
	public List<Produto> lstProduto() {			
		String sql = "SELECT "
				+ "  idproduto, "
				+ "  produto, "
				+ "  codproduto, "
				+ "  idunidade, "
				+ "  unidade, "
				+ "  sigla, "
				+ "  preco, " 
				+ "  descricao, "
				+ "  uscadast, "
				+ "  dtcadast, "
				+ "  usmodifi, "
				+ "  dtmodifi "
				+ "FROM "
				+ "  vw_produtos ";
		List<Produto> lista = new ArrayList<Produto>();
		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
 
			while (rset.next()) {
				Produto produto = new Produto();				
				produto.setIdproduto(rset.getInt("idproduto"));
				produto.setProduto(rset.getString("produto"));
				produto.setCodproduto(rset.getString("codproduto"));
				produto.setIdunidade(rset.getInt("idunidade"));
				produto.setUnidade(rset.getString("unidade"));
				produto.setSigla(rset.getString("sigla"));
				produto.setPreco(rset.getFloat("preco"));
				produto.setDescricao(rset.getString("descricao"));
				produto.setUscadast(rset.getString("uscadast"));
				produto.setDtcadast(rset.getTimestamp("dtcadast"));
				produto.setUsmodifi(rset.getString("usmodifi"));
				produto.setDtmodifi(rset.getTimestamp("dtmodifi"));				
				lista.add(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {

				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lista;
	}	
		
	@SuppressWarnings("static-access")
	public ArrayList<Object[]> tblProduto() {					
		String sql = "SELECT "
				+ "  idproduto, "
				+ "  produto, "
				+ "  codproduto, "
				+ "  idunidade, "
				+ "  unidade, "
				+ "  sigla, "
				+ "  preco, " 
				+ "  descricao, "
				+ "  uscadast, "
				+ "  dtcadast, "
				+ "  usmodifi, "
				+ "  dtmodifi "
				+ "FROM "
				+ "  vw_produtos "
				+ "ORDER BY "
				+ "  idproduto "
				+ "DESC ";
		
		ArrayList<Object[]> entidade = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery(); 			
			
			DateFormat dtf = new SimpleDateFormat(Global.funcao.getData().getDatahoraformat());			
			DecimalFormat dcf = new DecimalFormat(Global.funcao.getNumero().numeroformato.getFormatoTwo());
			
			while (rset.next()) {				
				entidade.add(new Object[] {
						rset.getInt("idproduto"),
						rset.getString("produto"),
						rset.getString("codproduto"),
						rset.getInt("idunidade"),		
						rset.getString("unidade"),		
						rset.getString("sigla"),						
						dcf.format(rset.getDouble("preco")),
						rset.getString("descricao"),
						rset.getString("uscadast"),																	
						rset.getTimestamp("dtcadast") != null ? dtf.format(rset.getTimestamp("dtcadast")) : rset.getTimestamp("dtcadast"),
						rset.getString("usmodifi"),
						rset.getTimestamp("dtmodifi") != null ? dtf.format(rset.getTimestamp("dtmodifi")) : rset.getTimestamp("dtmodifi")});								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {

				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return entidade;
	}	
	
	public ArrayList<Object[]> grdProduto() {					
		String sql = "SELECT "
				+ "  idproduto, "
				+ "  produto, "				
				+ "  uscadast, "
				+ "  dtcadast, "
				+ "  usmodifi, "
				+ "  dtmodifi "
				+ "FROM "
				+ "  vw_produtos "
				+ "ORDER BY "
				+ "  idproduto "
				+ "ASC ";
		
		ArrayList<Object[]> entidade = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery(); 			
			
			DateFormat dtf = new SimpleDateFormat(Global.funcao.getData().getDatahoraformat());						
			
			while (rset.next()) {				
				entidade.add(new Object[] {
						rset.getInt("idproduto"),
						rset.getString("produto"),						
						rset.getString("uscadast"),																	
						rset.getTimestamp("dtcadast") != null ? dtf.format(rset.getTimestamp("dtcadast")) : rset.getTimestamp("dtcadast"),
						rset.getString("usmodifi"),
						rset.getTimestamp("dtmodifi") != null ? dtf.format(rset.getTimestamp("dtmodifi")) : rset.getTimestamp("dtmodifi")});								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {

				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return entidade;
	}
	
	public Produto setProduto(int idproduto) {			
		String sql = "SELECT "
				+ "  idproduto, "
				+ "  produto, "
				+ "  codproduto, "
				+ "  idunidade, "
				+ "  unidade, "
				+ "  sigla, "
				+ "  preco, " 
				+ "  descricao, "
				+ "  uscadast, "
				+ "  dtcadast, "
				+ "  usmodifi, "
				+ "  dtmodifi "
				+ "FROM "
				+ "  vw_produtos "
				+ "WHERE "
				+ "  idproduto = ? ";

		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;
		Produto produto = new Produto();
		
		try {
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idproduto);	
			rset = pstm.executeQuery();
			rset.next();
			
			produto.setIdproduto(rset.getInt("idproduto"));
			produto.setProduto(rset.getString("produto"));
			produto.setCodproduto(rset.getString("codproduto"));
			produto.setIdunidade(rset.getInt("idunidade"));
			produto.setUnidade(rset.getString("unidade"));
			produto.setSigla(rset.getString("sigla"));
			produto.setPreco(rset.getFloat("preco"));
			produto.setDescricao(rset.getString("descricao"));
			produto.setUscadast(rset.getString("uscadast"));
			produto.setDtcadast(rset.getTimestamp("dtcadast"));
			produto.setUsmodifi(rset.getString("usmodifi"));
			produto.setDtmodifi(rset.getTimestamp("dtmodifi"));				
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {

				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return produto;
	}

}
