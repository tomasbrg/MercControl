/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package merccontrol;


import java.util.ArrayList;

public class Estoque {
    private ArrayList<Produto> produtos;
    
    public Estoque() {
        this.produtos = new ArrayList<>();
    }
    
    public void adicionarProduto(Produto p) {
        produtos.add(p);
        System.out.println("Produto adicionado com sucesso!");
    }
    
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\n=== PRODUTOS CADASTRADOS ===");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + ". " + produtos.get(i));
            }
            System.out.println("Total: " + produtos.size() + " produtos");
        }
    }
    
    public Produto buscarPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    public boolean removerPorCodigo(int codigo) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codigo) {
                produtos.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void atualizarQuantidade(int codigo, int novaQuantidade) {
        Produto p = buscarPorCodigo(codigo);
        if (p != null) {
            p.setQuantidade(novaQuantidade);
            System.out.println("Quantidade atualizada!");
        }
    }
    
    public double calcularValorTotal() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.getPreco() * p.getQuantidade();
        }
        return total;
    }
}