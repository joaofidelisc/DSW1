package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends Dao {

	public void insert(Cliente cliente) {

        String sql = "INSERT INTO cliente (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setLong(3, cliente.getCpf());
            statement.setString(4, cliente.getNome());
            statement.setLong(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            String data = (new SimpleDateFormat("yyyy-MM-dd").format(cliente.getData_nascimento()));
            statement.setDate(7, java.sql.Date.valueOf(data));
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Cliente order by cpf"; //fazer ordenação por consulta depois

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long cpf = resultSet.getLong("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                Long telefone = resultSet.getLong("telefone");
                String sexo = resultSet.getString("sexo");
                Date data_nascimento = resultSet.getDate("data_nascimento");
                
                Cliente cliente = new Cliente(email, senha,cpf, nome, telefone, sexo, data_nascimento);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET email = ?, senha = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ?";
        sql += " WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getNome());
            statement.setLong(4, cliente.getTelefone());
            statement.setString(5, cliente.getSexo());
            String data = (new SimpleDateFormat("yyyy-MM-dd").format(cliente.getData_nascimento()));
            statement.setDate(6, java.sql.Date.valueOf(data));
            statement.setLong(7, cliente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long cpf_parametro) {
        Cliente clt = null;

        String sql = "SELECT * from Cliente where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf_parametro);
            ResultSet resultSet = statement.executeQuery();
            if ( resultSet.next() ) {
            	
            	String email = resultSet.getString("email");
            	String senha = resultSet.getString("senha");
            	Long cpf = resultSet.getLong("cpf");
            	String nome = resultSet.getString("nome");
                Long telefone = resultSet.getLong("telefone");
                String sexo = resultSet.getString("sexo");
                Date data_nascimento = resultSet.getDate("data_nascimento");

                clt = new Cliente(email, senha, cpf, nome, telefone, sexo, data_nascimento);
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
