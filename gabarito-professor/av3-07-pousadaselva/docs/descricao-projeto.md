# PousadaSelva — Gestão de Reservas de Pousada de Ecoturismo

## Contexto
O ecoturismo na Amazônia (passeios à Floresta Amazônica, encontro das águas, praias fluviais e igarapés) movimenta pousadas e hotéis de selva na região de Manaus e do Rio Negro. A gestão de reservas e pacotes turísticos exige controle detalhado.

## Objetivo
Desenvolver um sistema de reservas para uma pousada de ecoturismo, controlando quartos/bangalôs, hóspedes, pacotes de passeio e o cálculo de valores conforme o período de estadia.

## Requisitos funcionais
1. Cadastrar acomodações (quarto padrão, bangalô, suíte flutuante) com diária.
2. Cadastrar hóspedes nacionais e estrangeiros.
3. Cadastrar pacotes de passeio (trilha, observação de botos, pesca esportiva).
4. Registrar reservas com check-in/check-out e pacotes contratados.
5. Calcular valor total considerando diária × período + pacotes.
6. Aplicar tarifa diferenciada para hóspede estrangeiro (regra de negócio, ex.: taxa de serviço).

## Classes sugeridas
- Acomodacao (abstrata) — QuartoPadrao, Bangalo, SuiteFlutuante (herança)
- Hospede (abstrata) — HospedeNacional, HospedeEstrangeiro
- PacoteTuristico (nome, descrição, valor)
- Reserva (acomodacao, hospede, checkIn, checkOut, pacotes)
- CalculadoraTarifa (calcula valor total da reserva)

## Conceitos de POO a aplicar
- Herança em Acomodacao e Hospede
- Polimorfismo no cálculo de diária e taxas por tipo
- Composição (Reserva agrega lista de PacoteTuristico)
- Encapsulamento das regras tarifárias
