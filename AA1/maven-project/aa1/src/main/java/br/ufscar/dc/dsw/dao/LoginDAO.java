package br.ufscar.dc.dsw.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Login;

public class LoginDAO extends Dao{
	
	public LoginDAO() {
		
	}

	private Login buscaLoginNaTabela(String email_parametro, String tabela) throws SQLException {
		Login login = null;
		String sql = "select * from " + tabela + " WHERE email = ?";
		int tipoLogin = 0;
		
		if(tabela.equals("Administrador")) {
			tipoLogin = 1;
		}else if(tabela.equals("Profissional")) {
			tipoLogin = 2;
		}else if(tabela.equals("Cliente")) {
			tipoLogin = 3;
		}		
		
		Connection conn = this.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email_parametro);
		ResultSet rst = pst.executeQuery();
		if( rst.next() ) {//se tem alguém cadastrado
			String email = rst.getString("email");
			String senha = rst.getString("senha"); 
			login = new Login(email, senha, tipoLogin);

			rst.close();
	        pst.close();
	        conn.close();
	        return login;
		}
        
		rst.close();
        pst.close();
        conn.close();
        return null;
	}
	
	public Login buscaLoginPorEmail(String email_parametro) throws SQLException {
		Login login = null;
		login = buscaLoginNaTabela(email_parametro, "Administrador");
		if( login != null) {
			return login; 
		}else {
			login = buscaLoginNaTabela(email_parametro, "Profissional");
			if( login != null) {
				return login; 
			}else {
				login = buscaLoginNaTabela(email_parametro, "Cliente");
				if( login != null) {
					return login; 
				}
			}
		}

		return null;
	}

}
