# MonitoraRioNegro — Monitoramento de Níveis de Cheia e Seca

## Contexto
O Rio Negro, monitorado historicamente pelo Porto de Manaus e por instituições como o INPA, apresenta ciclos anuais de cheia e seca que afetam a vida ribeirinha. Um sistema de acompanhamento ajuda comunidades a se planejarem.

## Objetivo
Desenvolver um sistema que registre medições diárias do nível do rio em pontos de monitoramento, classifique o estado (cheia, seca, normal) e alerte quando níveis críticos forem atingidos.

## Requisitos funcionais
1. Cadastrar estações de monitoramento (nome, localização/município).
2. Registrar medições diárias de nível (em metros) por estação.
3. Classificar automaticamente o estado do rio conforme faixas de referência.
4. Emitir alerta quando o nível ultrapassar limites críticos (cheia/seca extrema).
5. Gerar histórico e variação do nível em um período (gráfico textual ou tabela).
6. Comparar dados entre duas ou mais estações.

## Classes sugeridas
- EstacaoMonitoramento (nome, município, listaMedições)
- Medicao (data, nivelMetros)
- ClassificadorNivel (interface: classificar(nivel))
- AlertaHidrologico (tipo, mensagem, medicaoOrigem)
- ComparadorEstacoes (utilitário estático)

## Conceitos de POO a aplicar
- Interface ClassificadorNivel implementada por diferentes estratégias de classificação
- Encapsulamento de faixas de referência
- Coleções (List<Medicao> ordenada por data)
- Tratamento de exceções para medições inválidas
