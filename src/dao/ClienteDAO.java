package DAO;

import merccontrol.Cliente;
import PastaConexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, endereco) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getEndereco());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id";
        
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("endereco")
                );
                clientes.add(cliente);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientes;
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nome LIKE ? ORDER BY id";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("endereco")
                );
                clientes.add(cliente);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientes;
    }
    
    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, endereco = ? WHERE id = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getEndereco());
            stmt.setInt(4, Integer.parseInt(cliente.getId()));
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}