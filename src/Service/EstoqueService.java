package Service;

import merccontrol.Produto;

/** Regras de quantidade do estoque, sem dependência das telas. */
public class EstoqueService {
    public void validarQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
    }

    public int calcularEstoqueAposVenda(Produto produto, int quantidadeVendida) {
        if (produto == null) throw new IllegalArgumentException("Produto não encontrado.");
        if (quantidadeVendida <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        if (quantidadeVendida > produto.getQuantidade()) throw new IllegalArgumentException("Estoque insuficiente.");
        return produto.getQuantidade() - quantidadeVendida;
    }
}
