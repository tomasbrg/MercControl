package Service;

import java.util.Arrays;
import merccontrol.ItemVenda;
import merccontrol.Produto;

/** Executar este main para testar regras sem conectar ao MySQL. */
public class TesteRegras {
    public static void main(String[] args) {
        EstoqueService estoque = new EstoqueService();
        Produto produto = new Produto("1", "Parafuso", "Ferragens", false, 5.0, 10);
        conferir(estoque.calcularEstoqueAposVenda(produto, 3) == 7, "Baixa de estoque");
        conferir(new VendaService().calcularTotal(Arrays.asList(
                new ItemVenda("1", "Parafuso", 5.0, 2),
                new ItemVenda("2", "Porca", 2.0, 3))) == 16.0, "Total da venda");
        boolean bloqueou = false;
        try { estoque.calcularEstoqueAposVenda(produto, 11); }
        catch (IllegalArgumentException e) { bloqueou = true; }
        conferir(bloqueou, "Estoque insuficiente");
        bloqueou = false;
        try { estoque.validarQuantidade(-1); }
        catch (IllegalArgumentException e) { bloqueou = true; }
        conferir(bloqueou, "Quantidade negativa");
        System.out.println("Testes concluídos.");
    }
    private static void conferir(boolean condicao, String nome) {
        if (!condicao) throw new AssertionError("Falhou: " + nome);
        System.out.println("OK - " + nome);
    }
}
