# NaveFluvial — Controle de Frota de Transporte Fluvial de Passageiros

## Contexto
O deslocamento entre Manaus e municípios do interior (Parintins, Tefé, Itacoatiara) é feito majoritariamente por barcos regionais e lanchas rápidas. Empresas de navegação precisam controlar embarcações, viagens e ocupação de camarotes/redes.

## Objetivo
Criar um sistema de controle de frota fluvial que gerencie embarcações, rotas, viagens e a venda de passagens (rede ou camarote), calculando a ocupação de cada viagem.

## Requisitos funcionais
1. Cadastrar embarcações (nome, capacidade de redes, capacidade de camarotes).
2. Cadastrar rotas (origem, destino, distância em km, duração estimada).
3. Agendar viagens vinculando embarcação e rota, com data/hora de partida.
4. Vender passagens (tipo rede ou camarote) associadas a uma viagem.
5. Impedir venda acima da capacidade da embarcação (regra de negócio).
6. Relatório de taxa de ocupação por viagem e por rota.

## Classes sugeridas
- Embarcacao (nome, capacidadeRede, capacidadeCamarote)
- Rota (origem, destino, distanciaKm)
- Viagem (embarcacao, rota, dataPartida, listaPassagens)
- Passagem (abstrata) — PassagemRede, PassagemCamarote (herança)
- Passageiro (nome, CPF, documento)

## Conceitos de POO a aplicar
- Herança e classe abstrata Passagem
- Polimorfismo no cálculo de preço por tipo de passagem
- Encapsulamento das regras de capacidade
- Composição (Viagem possui lista de Passagem)
