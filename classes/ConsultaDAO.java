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

import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends Dao {

	public void insert(Consulta consulta) {

        String sql = "INSERT INTO consulta (num_consulta, dia_horario, profissional, cancelada, cliente) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getNum_consulta());
            statement.setDate(2, consulta.getDia_horario());
            statement.setInt(3, consulta.getProfissional());
            statement.setBoolean(4, consulta.isCancelada());
            statement.setInt(5, consulta.getCliente());

            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * from Consulta order by cpf"; //fazer ordenação por consulta depois

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
                
                Consulta consulta = new Consulta(email, senha, cpf, nome, areaDeConhecimento, especialidade, localPDF);
                listaProfissionais.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM Consulta where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET email = ?, senha = ?, nome = ?, areaDeConhecimento = ?, especialidade = ?, localPDF = ?";
        sql += " WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getEmail());
            statement.setString(2, consulta.getSenha());
            statement.setString(3, consulta.getNome());
            statement.setLong(4, consulta.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta get(long cpf_parametro) {
        Consulta clt = null;

        String sql = "SELECT * from Consulta where cpf = ?";

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


                clt = new Consulta(email, senha, cpf, nome, areaDeConhecimento, especialidade, localPDF);
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
