# AgroCoop — Gestão de Cooperativa Agrícola Familiar

## Contexto
Cooperativas agrícolas familiares do interior do Amazonas (municípios como Iranduba e Careiro) reúnem produtores de mandioca, farinha, hortaliças e frutas regionais para comercialização conjunta, muitas vezes vinculada a programas como o PAA/PNAE.

## Objetivo
Criar um sistema de gestão para cooperativa agrícola familiar que controle produtores associados, produção entregue, e a distribuição de receita entre cooperados conforme volume entregue.

## Requisitos funcionais
1. Cadastrar produtores associados e suas propriedades/comunidades.
2. Cadastrar produtos agrícolas com unidade de medida (kg, saca, litro).
3. Registrar entregas de produção por produtor e por período.
4. Calcular o valor a receber de cada produtor conforme preço de mercado.
5. Consolidar entregas por produto para venda institucional (PAA/PNAE).
6. Gerar relatório de ranking de produtores por volume entregue.

## Classes sugeridas
- Produtor (nome, comunidade, propriedade)
- ProdutoAgricola (nome, unidadeMedida, precoReferencia)
- Entrega (produtor, produto, quantidade, data)
- Cooperativa (gerencia produtores e consolida entregas)
- CalculadoraReceita (calcula valor a pagar por produtor)

## Conceitos de POO a aplicar
- Encapsulamento do cálculo de receita por produtor
- Coleções (Map<Produtor, List<Entrega>>)
- Uso de interface Calculavel para diferentes formas de precificação
- Tratamento de exceções para quantidades/preços inválidos
