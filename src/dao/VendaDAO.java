package DAO;

import PastaConexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendaDAO {
    
    public int registrarVenda(int clienteId, double total) {
    String sql = "INSERT INTO vendas (cliente_id, total) VALUES (?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            
            if (clienteId > 0) {
                stmt.setInt(1, clienteId);
            } else {
                stmt.setNull(1, Types.INTEGER);  // Usar setNull em vez de passar null diretamente
            }
            stmt.setDouble(2, total);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public boolean adicionarItemVenda(int vendaId, int produtoId, int quantidade, double precoUnitario) {
        String sql = "INSERT INTO venda_itens (venda_id, produto_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            double subtotal = quantidade * precoUnitario;
            stmt.setInt(1, vendaId);
            stmt.setInt(2, produtoId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, precoUnitario);
            stmt.setDouble(5, subtotal);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Map<String, Object>> getRelatorioVendas() {
        List<Map<String, Object>> relatorio = new ArrayList<>();
        String sql = "SELECT v.id, c.nome as cliente, v.data_venda, v.total " +
                     "FROM vendas v LEFT JOIN clientes c ON v.cliente_id = c.id " +
                     "ORDER BY v.data_venda DESC";
        
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> venda = new HashMap<>();
                venda.put("id", rs.getInt("id"));
                venda.put("cliente", rs.getString("cliente") != null ? rs.getString("cliente") : "Cliente não informado");
                venda.put("data", rs.getTimestamp("data_venda"));
                venda.put("total", rs.getDouble("total"));
                relatorio.add(venda);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return relatorio;
    }
    
    public double getTotalVendas() {
        String sql = "SELECT COALESCE(SUM(total), 0) as total FROM vendas";
        
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}
