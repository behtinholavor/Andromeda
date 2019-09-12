package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Veiculo;
import util.Conexao;
import util.Global;


public class VeiculoDao {

	public VeiculoDao() {
		
	}
		
	public void insVeiculo(Veiculo veiculo) {
		String sql = "INSERT INTO veiculos("
				+ "placa, marca, modelo, uscadast, dtcadast, usmodifi, dtmodifi)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
	
		Connection conn = null;
		PreparedStatement pstm = null;
				
		try {							
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);			
			pstm.setString(1, veiculo.getPlaca());
			pstm.setString(2, veiculo.getMarca());
			pstm.setString(3, veiculo.getModelo());				
			pstm.setString(4, veiculo.getUscadast());		
			pstm.setTimestamp(5, veiculo.getDtcadast());			
			pstm.setString(6, veiculo.getUsmodifi());
			pstm.setTimestamp(7, veiculo.getDtmodifi());																		
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
	
	public void delVeiculo(int idveiculo) {
		String sql = "DELETE FROM veiculos WHERE idveiculo = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
				
		try {							
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);						
			pstm.setInt(1, idveiculo);			
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
	
	public void atlVeiculo(Veiculo veiculo) {	
		String sql = "UPDATE veiculos SET "
				+ "placa=?, "
				+ "marca=?, "
				+ "modelo=?, "
				+ "usmodifi=?, "
				+ "dtmodifi=?"
				+ "WHERE idveiculo=?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
				
		try {							
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);			
			pstm.setString(1, veiculo.getPlaca());
			pstm.setString(2, veiculo.getMarca());
			pstm.setString(3, veiculo.getModelo());																
			pstm.setString(4, veiculo.getUsmodifi());
			pstm.setTimestamp(5, veiculo.getDtmodifi());								
			pstm.setInt(6, veiculo.getIdveiculo());														
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
	
	
	public List<Veiculo> lstVeiculo() {			
		String sql = "SELECT * FROM veiculos";
		List<Veiculo> lista = new ArrayList<Veiculo>();
		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;

		try {
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
 
			while (rset.next()) {
				Veiculo veiculo = new Veiculo();				
				veiculo.setIdveiculo(rset.getInt("idveiculo"));
				veiculo.setPlaca(rset.getString("placa"));
				veiculo.setMarca(rset.getString("marca"));
				veiculo.setModelo(rset.getString("modelo"));			
				veiculo.setUscadast(rset.getString("uscadast"));
				veiculo.setDtcadast(rset.getTimestamp("dtcadast"));
				veiculo.setUsmodifi(rset.getString("usmodifi"));
				veiculo.setDtmodifi(rset.getTimestamp("dtmodifi"));				
				lista.add(veiculo);
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
	
	public ArrayList<Object[]> tblVeiculo() {			
		String sql = "SELECT idveiculo, placa, marca, modelo, uscadast, dtcadast, usmodifi, dtmodifi FROM veiculos ORDER BY idveiculo DESC";		
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
						rset.getInt("idveiculo"),						
						rset.getString("placa"),
						rset.getString("marca"),												
						rset.getString("modelo"),											
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
	
	public ArrayList<Object[]> grdVeiculo() {			
		String sql = "SELECT idveiculo, placa, uscadast, dtcadast, usmodifi, dtmodifi FROM veiculos ORDER BY idveiculo DESC";		
		ArrayList<Object[]> entidade = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;
		
		DateFormat dtf = new SimpleDateFormat(Global.funcao.getData().getDatahoraformat());

		try {
			conn = Conexao.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery(); 									
			
			while (rset.next()) {				
				entidade.add(new Object[] {
						rset.getInt("idveiculo"),						
						rset.getString("placa"),								
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
	
	public Veiculo setVeiculo(int idveiculo) {			
		String sql = "SELECT * FROM veiculos WHERE idveiculo = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;		
		ResultSet rset = null;
		Veiculo veiculo = new Veiculo();
		
		try {
			conn = Conexao.createConnectionToPostgres();
			
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idveiculo);
			
			rset = pstm.executeQuery();
			rset.next();
			
			veiculo.setIdveiculo(rset.getInt("idveiculo"));
			veiculo.setPlaca(rset.getString("placa"));
			veiculo.setMarca(rset.getString("marca"));
			veiculo.setModelo(rset.getString("modelo"));			
			veiculo.setUscadast(rset.getString("uscadast"));
			veiculo.setDtcadast(rset.getTimestamp("dtcadast"));
			veiculo.setUsmodifi(rset.getString("usmodifi"));
			veiculo.setDtmodifi(rset.getTimestamp("dtmodifi"));								
					
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

		return veiculo;
	}

}
