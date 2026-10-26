package Service;

import merccontrol.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstoqueServiceTest {
    private EstoqueService service;
    private Produto produto;

    @BeforeEach
    void preparar() {
        service = new EstoqueService();
        produto = new Produto("1", "Parafuso", "Ferragens", false, 2.50, 10);
    }

    @Test
    void vendaDiminuiEstoque() {
        assertEquals(7, service.calcularEstoqueAposVenda(produto, 3));
    }

    @Test
    void vendaDeTodoEstoqueResultaEmZero() {
        assertEquals(0, service.calcularEstoqueAposVenda(produto, 10));
    }

    @Test
    void naoPermiteVendaAcimaDoEstoque() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularEstoqueAposVenda(produto, 11));
    }

    @Test
    void naoPermiteQuantidadeZeroNaVenda() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularEstoqueAposVenda(produto, 0));
    }

    @Test
    void naoPermiteProdutoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularEstoqueAposVenda(null, 1));
    }

    @Test
    void naoPermiteEstoqueNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> service.validarQuantidade(-1));
    }

    @Test
    void permiteEstoqueZero() {
        assertDoesNotThrow(() -> service.validarQuantidade(0));
    }
}
