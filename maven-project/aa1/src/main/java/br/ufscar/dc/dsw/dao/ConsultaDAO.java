package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;

public class ConsultaDAO extends Dao {

	public void insert(Consulta consulta) {

        String sql = "INSERT INTO consulta (num_consulta, data_consulta, hora_consulta, cpf_profissional, cpf_cliente, cancelada) VALUES (DEFAULT, ?, ?, ?, ?,?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            String data = (new SimpleDateFormat("yyyy-MM-dd").format(consulta.getData_consulta()));
            statement.setDate(1, java.sql.Date.valueOf(data) );
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

	
	
    public List<Consulta> getAllByEmail(String email, int tipoLogin) {
    	Long cpf = null;
        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * from Consulta where";
        if( tipoLogin == 2 )//se for profissional
        	sql += " cpf_profissional = ?";
        else if( tipoLogin == 3 )//se for cliente
        	sql += " cpf_cliente = ?";
        sql += "order by data_consulta ASC, hora_consulta ASC ";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            if( tipoLogin == 2){ 
            	ProfissionalDAO prf = new ProfissionalDAO();
            	cpf = prf.getCpfUsuarioPorEmail(email);
        	}
            else if (tipoLogin == 3) {
            	ClienteDAO clt = new ClienteDAO();
            	cpf = clt.getCpfUsuarioPorEmail(email);
            }
            
            statement.setLong(1, cpf);
            ResultSet resultSet = statement.executeQuery();
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
        String sql = "UPDATE Consulta SET cancelada = ?";
        sql += " WHERE num_consulta = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, consulta.isCancelada() );
            statement.setLong(2, consulta.getNum_consulta());                       
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
    
    public boolean verificaSeClienteDisponivel(Date data_consulta, Time hora_consulta, Long cpf_cliente) throws SQLException {
    	String sql = "SELECT * FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND cpf_cliente = ?";
    	Connection conn = this.getConnection();
    	
    	PreparedStatement pst = conn.prepareStatement(sql);
    	pst.setDate(1, data_consulta);
    	pst.setTime(2, hora_consulta);
    	pst.setLong(3,cpf_cliente);
    	ResultSet rst = pst.executeQuery();
    	if( rst.next() ) {
    		rst.close();
    		pst.close();
    		conn.close();    		
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean verificaSeProfissionalDisponivel(Date data_consulta, Time hora_consulta, Long cpf_profissional) throws SQLException {
    	String sql = "SELECT * FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND cpf_profissional= ?";
    	Connection conn = this.getConnection();
    	
    	PreparedStatement pst = conn.prepareStatement(sql);
    	pst.setDate(1, data_consulta);
    	pst.setTime(2, hora_consulta);
    	pst.setLong(3,cpf_profissional);
    	ResultSet rst = pst.executeQuery();
    	if( rst.next() ) {
    		rst.close();
    		pst.close();
    		conn.close();    		
    		return false;
    	}
    	
    	return true;
    }
}
