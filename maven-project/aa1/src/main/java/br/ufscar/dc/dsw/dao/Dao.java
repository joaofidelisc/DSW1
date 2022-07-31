package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


abstract public class Dao {
	
	public Dao() {
		try {
			Class.forName("org.postgresql.Driver");
		}catch( ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Connection getConnection() throws SQLException{
		
		String url = "jdbc:postgresql://localhost:5432/bd_dsw";
		
		return DriverManager.getConnection(url, "postgres", "postgres");
	}
	
	private String verificaNaTabela(String tabelaSendoVerificada, String email_parametro) throws SQLException {
		String sql = "select * from " + tabelaSendoVerificada + " WHERE email = ?";
			
		Connection conn = this.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email_parametro);
		ResultSet rst = pst.executeQuery();
		if( rst.next() ) {//se tem alguém cadastrado
			String email = rst.getString("email");
			if(email_parametro.equalsIgnoreCase(email)) {//esta nesse tabela
				rst.close();
		        pst.close();
		        conn.close();
		        return tabelaSendoVerificada;
			}			
		}
        
		rst.close();
        pst.close();
        conn.close();
		return "";
	}
	
	private String getTabelaPorEmail(String email_parametro) throws SQLException {
		String tabela = "";

		tabela = verificaNaTabela("Profissional", email_parametro );
		if( !tabela.equalsIgnoreCase("") ) {
			return tabela; 
		}else {
			tabela = verificaNaTabela( "Cliente", email_parametro );
			if( !tabela.equalsIgnoreCase("") ) {
				return tabela; 
			}
		}	

		return "";
	}
	
	public Long getCpfUsuarioPorEmail( String email_parametro) throws SQLException {
		String tabela = getTabelaPorEmail(email_parametro);
		
		if(tabela.equals("")) return null;
		
		String sql = "select * from " + tabela + " WHERE email = ?";
		
		Connection conn = this.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email_parametro);
		ResultSet rst = pst.executeQuery();
		if( rst.next() ) {//se tem alguém cadastrado
			Long cpf = rst.getLong("cpf");
			rst.close();
	        pst.close();
	        conn.close();
			return cpf;
				
		}
		rst.close();
        pst.close();
        conn.close();
		
		return null;		
	}
	
	private boolean verificaCpfNaTabela(String tabelaSendoVerificada, Long cpf_parametro) throws SQLException {
		String sql = "select * from " + tabelaSendoVerificada + " WHERE cpf = ?";
		
		Connection conn = this.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, cpf_parametro);
		ResultSet rst = pst.executeQuery();
		if( rst.next() ) {//se tem alguém cadastrado
			Long cpf = rst.getLong("cpf");
			if( cpf.longValue() == cpf_parametro.longValue()) {//esta nesse tabela//if extra, não é necessário pq a pesquisa busca os registros com o cpf do parametro
				rst.close();
		        pst.close();
		        conn.close();
		        return true;
			}			
		}
        
		rst.close();
        pst.close();
        conn.close();
		return false;
	}
	
	public boolean cpfJaCadastrado(Long cpf_parametro) throws SQLException {
		boolean cadastrado = false;

		cadastrado = verificaCpfNaTabela("Profissional", cpf_parametro );
		if( cadastrado ) {
			return true; 
		}else {
			cadastrado = verificaCpfNaTabela( "Cliente", cpf_parametro );
			if( cadastrado ) {
				return true; 
			}
		}	
		return false;
	}
}
