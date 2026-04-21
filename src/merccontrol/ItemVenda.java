
package merccontrol;

public class ItemVenda {
    private String produtoId;
    private String nome;
    private double preco;
    private int quantidade;

    public ItemVenda(String produtoId, String nome, double preco, int quantidade) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getProdutoId() { return produtoId; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public double getSubtotal() { return preco * quantidade; }
}
