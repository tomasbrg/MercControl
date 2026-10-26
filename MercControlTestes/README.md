# MercControlTestes - Etapa 7

Projeto de testes JUnit 5 para a regra de estoque do MercControl da Etapa 6.

As classes `Service.EstoqueService` e `merccontrol.Produto` foram copiadas sem alterações do projeto da Etapa 6, para que o projeto de testes possa ser aberto separadamente no NetBeans sem precisar do MySQL. O sistema desktop original continua no projeto anterior.

## Abrir no NetBeans

1. Arquivo > Abrir Projeto > escolha a pasta `MercControlTestes`.
2. Aguarde o Maven baixar as dependências do JUnit (precisa de internet na primeira execução).
3. Clique com o botão direito no projeto > Testar (Alt+F6).
4. Veja o resultado na janela de saída do NetBeans.

Também pode usar `mvn test` na pasta do projeto se o Maven estiver instalado.

## Publicar no repositório já existente

Na pasta local clonada de `https://github.com/tomasbrg/MercControl`, crie/acesse a branch de testes, copie esta pasta `MercControlTestes` para a raiz e execute:

```
git switch -c testes-etapa7
git add MercControlTestes
git commit -m "Adiciona testes JUnit do estoque - Etapa 7"
git push -u origin testes-etapa7
```

Se a branch já existir, use `git switch testes-etapa7` em vez de `git switch -c testes-etapa7`. Não use `git init` novamente. Se o GitHub pedir login, autentique com a conta que tem permissão de escrita no repositório.

Após o push, faça uma captura da página da branch mostrando `MercControlTestes` e o commit. Coloque a captura em `Evidencia_GitHub/` e inclua no ZIP de entrega. Não foi gerada evidência falsa.
