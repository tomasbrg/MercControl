
package merccontrol;

import java.util.Scanner;

public class MercControl {

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();
        int opcao;
        
        do {
            System.out.println("\n=== CADASTRO DE PRODUTOS ===");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Buscar Produto por Código");
            System.out.println("4. Remover Produto");
            System.out.println("5. Atualizar Quantidade");
            System.out.println("6. Valor Total do Estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO PRODUTO ---");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    
                    System.out.print("Preço: R$");
                    double preco = scanner.nextDouble();
                    
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    
                    Produto p = new Produto(nome, codigo, preco, quantidade);
                    estoque.adicionarProduto(p);
                    break;
                    
                case 2:
                    estoque.listarProdutos();
                    break;
                    
                case 3:
                    System.out.print("\nDigite o código do produto: ");
                    int codBusca = scanner.nextInt();
                    Produto encontrado = estoque.buscarPorCodigo(codBusca);
                    
                    if (encontrado != null) {
                        System.out.println("Produto encontrado: " + encontrado);
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                    
                case 4:
                    System.out.print("\nDigite o código do produto a remover: ");
                    int codRemover = scanner.nextInt();
                    
                    if (estoque.removerPorCodigo(codRemover)) {
                        System.out.println("Produto removido!");
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                    
                case 5:
                    System.out.print("\nDigite o código do produto: ");
                    int codAtualizar = scanner.nextInt();
                    System.out.print("Nova quantidade: ");
                    int novaQtd = scanner.nextInt();
                    
                    estoque.atualizarQuantidade(codAtualizar, novaQtd);
                    break;
                    
                case 6:
                    double total = estoque.calcularValorTotal();
                    System.out.printf("\nValor total do estoque: R$%.2f\n", total);
                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
}
    
    

