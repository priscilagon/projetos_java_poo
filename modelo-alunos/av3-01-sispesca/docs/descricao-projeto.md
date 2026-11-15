# SisPesca — Gestão de Cooperativa de Piscicultura de Tambaqui

## Contexto
As cooperativas de piscicultura no Amazonas (como as do município de Rio Preto da Eva e Manacapuru) movimentam a criação de tambaqui, pirarucu e matrinxã em tanques-rede e viveiros escavados. O controle manual de lotes, alimentação e biometria gera perdas de produtividade.

## Objetivo
Desenvolver um sistema que controle o cadastro de piscicultores, tanques/viveiros, lotes de peixes, ciclos de alimentação e biometria (peso/tamanho), calculando a taxa de conversão alimentar de cada lote.

## Requisitos funcionais
1. Cadastrar piscicultores cooperados e suas propriedades.
2. Cadastrar tanques-rede/viveiros com capacidade e espécie criada.
3. Registrar lotes de peixes com data de povoamento e quantidade de alevinos.
4. Registrar biometrias periódicas (peso médio, comprimento) por lote.
5. Calcular a taxa de conversão alimentar (ração consumida / ganho de peso).
6. Gerar relatório de produtividade por tanque e por cooperado.

## Classes sugeridas
- Cooperado (nome, CPF, propriedade)
- TanqueRede (código, capacidade, espécie) — Viveiro (extends/implementa interface Criadouro)
- LotePeixe (data povoamento, quantidade, espécie)
- Biometria (data, pesoMedio, comprimentoMedio)
- Racao (tipo, quantidadeKg, custo)
- RelatorioProdutividade (calcula indicadores)

## Conceitos de POO a aplicar
- Herança (Viveiro e TanqueRede especializando Criadouro)
- Encapsulamento (atributos privados com getters/setters)
- Polimorfismo (métodos de cálculo de capacidade sobrescritos)
- Coleções (List<LotePeixe>, List<Biometria>)
