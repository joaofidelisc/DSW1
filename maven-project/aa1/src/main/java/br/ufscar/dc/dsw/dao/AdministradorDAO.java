package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Administrador;
import br.ufscar.dc.dsw.domain.Profissional;

public class AdministradorDAO extends Dao{
    public Profissional get(Long email_parametro) {
        Profissional clt = null;

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
            
                Administrador admin = new Administrador(email, senha, nome);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clt;
    }
}
