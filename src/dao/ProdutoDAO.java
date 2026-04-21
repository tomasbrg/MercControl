package DAO;

import merccontrol.Produto;
import PastaConexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, tipo, preco, quantidade, vendido) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setBoolean(5, produto.isVendido());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos ORDER BY id";
        
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Produto produto = new Produto(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getBoolean("vendido"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                );
                produtos.add(produto);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produtos;
    }
    
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Produto(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getBoolean("vendido"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE nome LIKE ? ORDER BY id";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getBoolean("vendido"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade")
                );
                produtos.add(produto);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produtos;
    }
    
    public boolean atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, tipo = ?, preco = ?, quantidade = ?, vendido = ? WHERE id = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setBoolean(5, produto.isVendido());
            stmt.setInt(6, Integer.parseInt(produto.getId()));
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizarQuantidade(int id, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, quantidade);
            stmt.setInt(2, id);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        
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
