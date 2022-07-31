package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Administrador;

public class AdministradorDAO extends Dao{
    public Administrador get(Long email_parametro) {
        Administrador admin = null;

        String sql = "SELECT * from Profissional where email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, email_parametro);
            ResultSet resultSet = statement.executeQuery();
            if ( resultSet.next()) {
            	String email = resultSet.getString("email");
            	String senha = resultSet.getString("senha");
            	String nome = resultSet.getString("nome");
            
                admin = new Administrador(email, senha, nome);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}