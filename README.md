# MercControl - Etapa 6

Este é o mesmo projeto desktop MercControl da etapa anterior, com as telas originais (`View/*.java` e `View/*.form`) mantidas.

## O que mudou
- `Service/EstoqueService.java`: regras de quantidade e validação de estoque.
- `Service/VendaService.java`: cálculo e registro de venda, antes dentro de `TelaVenda`.
- `View/TelaVenda.java`: chama `VendaService`, mantendo o formulário original do NetBeans; corrige atualização da quantidade.
- `merccontrol/Produto.java`: mantém compatibilidade com o menu de console antigo.
- `Service/TesteRegras.java`: testes simples pelo `main()` sem banco.

## Executar
Abra a pasta MercControl no NetBeans e execute `View.TelaLogin`. Para usar as telas, configure MySQL, banco `merccontrol`, tabelas existentes e driver MySQL Connector/J no projeto. Os arquivos do banco e o driver não foram fornecidos; portanto, a execução com MySQL depende desse ambiente.

Para testar sem banco, execute o arquivo `Service/TesteRegras.java` (Run File). O `main` testa estoque e cálculo de venda.

## GitHub
Crie um repositório novo e publique esta pasta. Inclua no relatório o link e prints reais do repositório. Não envie senhas nem configurações pessoais do NetBeans.
