package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.apache.jasper.tagplugins.jstl.core.ForEach;
//import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends Dao {

	public void insert(Profissional profissional) {

        String sql = "INSERT INTO profissional (email, senha, cpf, nome, areaConhecimento, especialidade, local_pdf) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setLong(3, profissional.getCpf());
            statement.setString(4, profissional.getNome());
            statement.setString(5, profissional.getAreaConhecimento());
            statement.setString(6, profissional.getEspecialidade());
            statement.setString(7, profissional.getLocal_pdf());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll(String[] checkArea, String[] checkEspecialidade) {

        List<Profissional> listaProfissionais = new ArrayList<>();
        String sql = "SELECT * from Profissional"; //fazer ordenação por consulta depois
        if(checkArea != null || checkEspecialidade != null){
        	sql+=" WHERE";
        	if(checkArea != null) {
        		sql += " areaConhecimento in (";
				for(String i: checkArea){
						sql += "'"+ i + "'" +",";
        		}
				sql = sql.substring(0, sql.length() -1 );//tira a ultima virgula
				sql+=")";
				if(checkEspecialidade != null)
					sql+=" or ";
        	}
        	if(checkEspecialidade != null) {
        		sql += " especialidade in ( ";
				for(String i: checkEspecialidade){
						sql += "'" + i + "'" + ",";
        		}
				sql = sql.substring(0, sql.length() -1 );//tira a ultima virgula
				sql+=")";
        	}
        }
        sql+=" ORDER BY cpf";
        
        
        

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long cpf = resultSet.getLong("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String areaConhecimento = resultSet.getString("areaConhecimento");
                String especialidade = resultSet.getString("especialidade");
                String local_pdf = resultSet.getString("local_pdf");
                
                Profissional profissional = new Profissional(email, senha, cpf, nome, areaConhecimento, especialidade, local_pdf);
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
        String sql = "UPDATE Profissional SET email = ?, senha = ?, nome = ?, areaConhecimento = ?, especialidade = ?, local_pdf = ?";
        sql += " WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getEmail());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getNome());
            statement.setString(4, profissional.getAreaConhecimento());
            statement.setString(5, profissional.getEspecialidade());
            statement.setString(6, profissional.getLocal_pdf());
            statement.setLong(7, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long cpf_parametro) {
        Profissional clt = null;

        String sql = "SELECT * from Profissional where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf_parametro);
            ResultSet resultSet = statement.executeQuery();
            if ( resultSet.next()) {
            	String email = resultSet.getString("email");
            	String senha = resultSet.getString("senha");
            	Long cpf = resultSet.getLong("cpf");
            	String nome = resultSet.getString("nome");
            	String areaConhecimento = resultSet.getString("areaConhecimento");
            	String especialidade = resultSet.getString("especialidade");
            	String local_pdf = resultSet.getString("local_pdf");


                clt = new Profissional(email, senha, cpf, nome, areaConhecimento, especialidade, local_pdf);
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
