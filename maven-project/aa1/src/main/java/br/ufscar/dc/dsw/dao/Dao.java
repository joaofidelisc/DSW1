package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
