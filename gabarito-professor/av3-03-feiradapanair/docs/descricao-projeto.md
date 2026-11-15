# FeiraDaPanair — Gestão de Feirantes e Produtos Regionais

## Contexto
Feiras livres como a da Panair e a do Manaus Moderna comercializam produtos regionais: açaí, tucumã, farinha, peixes e artesanato indígena. A organização precisa controlar feirantes, bancas e vendas diárias.

## Objetivo
Construir um sistema de gestão de feira que cadastre feirantes, bancas, produtos regionais e registre vendas diárias, gerando o produto mais vendido do dia.

## Requisitos funcionais
1. Cadastrar feirantes e a banca que ocupam.
2. Cadastrar produtos regionais com categoria (fruta, farinha, pescado, artesanato).
3. Registrar vendas diárias por produto e por banca.
4. Calcular o faturamento diário total e por feirante.
5. Listar o(s) produto(s) mais vendido(s) no período.
6. Aplicar desconto para clientes que compram acima de determinado valor (regra de negócio).

## Classes sugeridas
- Feirante (nome, banca, CPF)
- Produto (abstrata) — ProdutoFruta, ProdutoArtesanato, ProdutoPescado (herança)
- Venda (produto, quantidade, data, feirante)
- Banca (numero, localização)
- RelatorioVendas (agrega vendas e calcula estatísticas)

## Conceitos de POO a aplicar
- Herança em Produto e subclasses regionais
- Polimorfismo no cálculo de preço/desconto por categoria
- Encapsulamento do estoque de cada produto
- Coleções para histórico de vendas (List/Map)
