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

        String sql = "SELECT * from Consulta order by dia_horario"; //fazer ordenação por consulta depois

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long num_consulta = resultSet.getLong("num_consulta");
                Date dia_horario = resultSet.getDate("dia_horario");
                int profissional = resultSet.getInt("profissional");
                boolean cancelada = resultSet.getBoolean("cancelada");
                int cliente = resultSet.getInt("cliente");
                
                Consulta consulta = new Consulta(num_consulta, dia_horario, profissional, cancelada, cliente);
                listaConsultas.add(consulta);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM Consulta where dia_horario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setDate(1, consulta.getDia_horario());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET num_consulta = ?, dia_horario = ?, profissional = ?, cancelada = ?, cliente = ?";
        sql += " WHERE num_consulta = ?";

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

    public Consulta get(long num_consulta) {
        Consulta clt = null;

        String sql = "SELECT * from Consulta where num_consulta = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, num_consulta);
            ResultSet resultSet = statement.executeQuery();
            boolean temAlgumRegistro = resultSet.first();
            if ( temAlgumRegistro ) {
                Long num_consulta = resultSet.getLong("num_consulta");
                Date dia_horario = resultSet.getDate("dia_horario");
                int profissional = resultSet.getInt("profissional");
                boolean cancelada = resultSet. getBoolean("cancelada");
                int cliente = resultSet.getInt("cliente");

                clt = new Consulta(num_consulta, dia_horario, profissional, cancelada, cliente);
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