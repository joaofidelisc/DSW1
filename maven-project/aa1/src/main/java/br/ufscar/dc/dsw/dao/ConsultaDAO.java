package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends Dao {

	public void insert(Consulta consulta) {

        String sql = "INSERT INTO consulta (num_consulta, data_consulta, hora_consulta, cpf_profissional, cpf_cliente, cancelada) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getNum_consulta());
            String data = (new SimpleDateFormat("yyyy-MM-dd").format(consulta.getData_consulta()));
            statement.setDate(2, java.sql.Date.valueOf(data) );
            statement.setTime(3, consulta.getHora_consulta() );
            statement.setLong(4, consulta.getCpf_profissional());
            statement.setLong(5, consulta.getCpf_cliente());
            statement.setBoolean(6, consulta.isCancelada());

            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * from Consulta order by data_consulta ASC, hora_consulta ASC"; //fazer ordenação por consulta depois

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long num_consulta = resultSet.getLong("num_consulta");
                Date data_consulta = resultSet.getDate("data_consulta");
                Time hora_consulta = resultSet.getTime("hora_consulta");
                Long cpf_profissional = resultSet.getLong("cpf_profissional");
                Long cpf_cliente = resultSet.getLong("cpf_cliente");
                boolean cancelada = resultSet.getBoolean("cancelada");
                
                
                Consulta consulta = new Consulta(num_consulta, data_consulta, hora_consulta, cpf_profissional, cpf_cliente, cancelada);
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
        String sql = "DELETE FROM Consulta where num_consulta = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getNum_consulta() );
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET data_consulta = ?, hora_consulta, cpf_profissional = ?, cpf_cliente = ?, cancelada = ?";
        sql += " WHERE num_consulta = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setDate(1, new java.sql.Date( consulta.getData_consulta().getTime() ));
            statement.setTime(2, consulta.getHora_consulta() );
            statement.setLong(3, consulta.getCpf_profissional());
            statement.setLong(4, consulta.getCpf_cliente());
            statement.setBoolean(5, consulta.isCancelada());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta get(Long num_consulta_parametro) {
        Consulta clt = null;

        String sql = "SELECT * from Consulta where num_consulta = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, num_consulta_parametro);
            ResultSet resultSet = statement.executeQuery();
            if ( resultSet.next() ) {
            	Long num_consulta = resultSet.getLong("num_consulta");
                Date data_consulta = resultSet.getDate("data_consulta");
                Time hora_consulta = resultSet.getTime("hora_consulta");
                Long cpf_profissional = resultSet.getLong("cpf_profissional");
                Long cpf_cliente = resultSet.getLong("cpf_cliente");
                boolean cancelada = resultSet.getBoolean("cancelada");

                clt = new Consulta(num_consulta, data_consulta, hora_consulta, cpf_profissional, cpf_cliente, cancelada);
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
