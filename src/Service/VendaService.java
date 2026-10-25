package Service;

import DAO.ProdutoDAO;
import DAO.VendaDAO;
import merccontrol.ItemVenda;
import merccontrol.Produto;
import java.util.List;

/** Retira da tela as validações e o registro de venda. */
public class VendaService {
    private final VendaDAO vendaDAO;
    private final ProdutoDAO produtoDAO;
    private final EstoqueService estoqueService;

    public VendaService() {
        this(new VendaDAO(), new ProdutoDAO(), new EstoqueService());
    }

    public VendaService(VendaDAO vendaDAO, ProdutoDAO produtoDAO, EstoqueService estoqueService) {
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
        this.estoqueService = estoqueService;
    }

    public double calcularTotal(List<ItemVenda> itens) {
        if (itens == null || itens.isEmpty()) throw new IllegalArgumentException("Carrinho vazio.");
        double total = 0;
        for (ItemVenda item : itens) {
            if (item.getQuantidade() <= 0 || item.getPreco() < 0) throw new IllegalArgumentException("Item inválido.");
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean registrarVenda(List<ItemVenda> itens) {
        double total = calcularTotal(itens);
        // Confere o estoque antes de registrar a venda.
        for (ItemVenda item : itens) {
            int id = Integer.parseInt(item.getProdutoId());
            estoqueService.calcularEstoqueAposVenda(produtoDAO.buscarPorId(id), item.getQuantidade());
        }
        int vendaId = vendaDAO.registrarVenda(-1, total);
        if (vendaId <= 0) return false;
        boolean sucesso = true;
        for (ItemVenda item : itens) {
            int id = Integer.parseInt(item.getProdutoId());
            Produto produto = produtoDAO.buscarPorId(id);
            int novaQuantidade = estoqueService.calcularEstoqueAposVenda(produto, item.getQuantidade());
            if (!vendaDAO.adicionarItemVenda(vendaId, id, item.getQuantidade(), item.getPreco())) sucesso = false;
            if (!produtoDAO.atualizarQuantidade(id, novaQuantidade)) sucesso = false;
        }
        return sucesso;
    }
}
