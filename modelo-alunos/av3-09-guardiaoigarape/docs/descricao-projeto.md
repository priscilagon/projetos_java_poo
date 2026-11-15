# GuardiãoIgarapé — Gestão de Voluntariado Ambiental

## Contexto
ONGs e projetos de educação ambiental em Manaus atuam na despoluição e monitoramento de igarapés urbanos (como o Igarapé do Quarenta e do Mindu). A organização de voluntários e ações de limpeza exige controle de participação.

## Objetivo
Desenvolver um sistema que gerencie voluntários, ações ambientais (mutirões de limpeza, plantio de mudas) e o registro de participação, calculando horas de voluntariado acumuladas.

## Requisitos funcionais
1. Cadastrar voluntários com dados pessoais e disponibilidade.
2. Cadastrar ações ambientais (tipo, local/igarapé, data, duração em horas).
3. Registrar a participação de voluntários em cada ação.
4. Calcular o total de horas de voluntariado por pessoa.
5. Emitir certificado (texto) para voluntários que atingirem carga horária mínima.
6. Relatório de ações por tipo (limpeza, plantio, educação ambiental).

## Classes sugeridas
- Voluntario (nome, contato, listaParticipacoes)
- AcaoAmbiental (abstrata) — Limpeza, Plantio, EducacaoAmbiental (herança)
- Participacao (voluntario, acao, horasCumpridas)
- GeradorCertificado (interface: gerar(voluntario))
- RelatorioAcoes (agrupa por tipo e local)

## Conceitos de POO a aplicar
- Herança em AcaoAmbiental e subtipos
- Interface GeradorCertificado com implementação específica
- Encapsulamento do cálculo de horas acumuladas
- Coleções (Map<Voluntario, Double> para totais de horas)
