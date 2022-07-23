// package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends Dao {

	public void insert(Profissional profissional) {

        String sql = "INSERT INTO profissional (email, senha, cpf, nome, areaDeConhecimento, especialidade, localPDF) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setLong(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getAreaDeConhecimento());
            statement.setString(6, profissional.getEspecialidade());
            statement.setString(7, profissional.getLocalPDF());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from Profissional order by cpf"; //fazer ordenação por consulta depois

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long cpf = resultSet.getLong("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String areaDeConhecimento = resultSet.getString("areaDeConhecimento");
                String especialidade = resultSet.getString("especialidade");
                String localPDF = resultSet.getString("localPDF");
                
                Profissional profissional = new Profissional(email, senha, cpf, nome, areaDeConhecimento, especialidade, localPDF);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissional where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET email = ?, senha = ?, nome = ?, areaDeConhecimento = ?, especialidade = ?, localPDF = ?";
        sql += " WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getNome());
            statement.setLong(4, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(long cpf_parametro) {
        Profissional clt = null;

        String sql = "SELECT * from Profissional where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf_parametro);
            ResultSet resultSet = statement.executeQuery();
            boolean temAlgumRegistro = resultSet.first();
            if ( temAlgumRegistro ) {
            	String email = resultSet.getString("email");
            	String senha = resultSet.getString("senha");
            	long cpf = resultSet.getLong("cpf");
            	String nome = resultSet.getString("nome");
            	String areaDeConhecimento = resultSet.getString("areaDeConhecimento");
            	String especialidade = resultSet.getString("especialidade");
            	String localPDF = resultSet.getString("localPDF");


                clt = new Profissional(email, senha, cpf, nome, areaDeConhecimento, especialidade, localPDF);
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
