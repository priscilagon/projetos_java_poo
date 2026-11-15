# FarmaVida — Controle de Estoque de Farmácia Comunitária

## Contexto
Farmácias comunitárias e postos de saúde em bairros de Manaus e em municípios do interior precisam controlar medicamentos com atenção especial a datas de validade, dado o clima quente e úmido da região amazônica que acelera a deterioração de alguns insumos.

## Objetivo
Criar um sistema de controle de estoque farmacêutico que gerencie entrada/saída de medicamentos, alerte sobre validade próxima e calcule o nível mínimo de reposição.

## Requisitos funcionais
1. Cadastrar medicamentos (nome, princípio ativo, lote, validade).
2. Registrar entradas (compras) e saídas (dispensação) de estoque.
3. Alertar medicamentos com validade a vencer em até 30 dias.
4. Alertar itens abaixo do estoque mínimo definido.
5. Gerar relatório de medicamentos mais dispensados.
6. Bloquear dispensação de medicamento vencido (regra de negócio).

## Classes sugeridas
- Medicamento (nome, principioAtivo, estoqueMinimo)
- Lote (codigo, quantidade, validade, medicamento)
- MovimentacaoEstoque (abstrata) — Entrada, Saida (herança)
- AlertaValidade / AlertaEstoqueBaixo
- Farmacia (gerencia lotes e movimentações)

## Conceitos de POO a aplicar
- Herança em MovimentacaoEstoque (Entrada/Saída)
- Polimorfismo no método aplicar() de cada movimentação
- Encapsulamento das regras de validade e estoque mínimo
- Uso de LocalDate para cálculo de dias até o vencimento
