# PousadaSelva — Gestão de Reservas de Pousada de Ecoturismo

> **Gabarito de referência da professora (Profa. Priscila Gonçalves).** Não distribuir aos alunos antes da avaliação.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Desenvolver um sistema de reservas para uma pousada de ecoturismo, controlando quartos/bangalôs, hóspedes, pacotes de passeio e o cálculo de valores conforme o período de estadia.

**Contexto:** O ecoturismo na Amazônia (passeios à Floresta Amazônica, encontro das águas, praias fluviais e igarapés) movimenta pousadas e hotéis de selva na região de Manaus e do Rio Negro. A gestão de reservas e pacotes turísticos exige controle detalhado.

### Requisitos funcionais
1. Cadastrar acomodações (quarto padrão, bangalô, suíte flutuante) com diária.
2. Cadastrar hóspedes nacionais e estrangeiros.
3. Cadastrar pacotes de passeio (trilha, observação de botos, pesca esportiva).
4. Registrar reservas com check-in/check-out e pacotes contratados.
5. Calcular valor total considerando diária × período + pacotes.
6. Aplicar tarifa diferenciada para hóspede estrangeiro (regra de negócio, ex.: taxa de serviço).

### Conceitos de POO aplicados
- Herança em Acomodacao e Hospede
- Polimorfismo no cálculo de diária e taxas por tipo
- Composição (Reserva agrega lista de PacoteTuristico)
- Encapsulamento das regras tarifárias

## 2. Equipe
Profa. Priscila Gonçalves — CETAM, Manaus (Linguagem de Programação III).

## 3. Pré-requisitos
- JDK 17 ou superior (`java -version` e `javac -version`)
- Git
- PostgreSQL 14+ **ou** MySQL 8+ (para a parte de persistência)
- Driver JDBC (`lib/` ou Maven — ver `lib/README.md`)
- Opcional: Maven 3.9+

## 4. Estrutura do repositório
```
src/main/java/
├── model/       # entidades de domínio, classes abstratas, interfaces
├── service/     # regras de negócio
├── exception/   # exceções customizadas
├── entity/      # classes que espelham as tabelas (persistência)
├── dao/         # acesso a dados (CRUD com JDBC)
├── ConexaoBD.java
├── TesteCRUD.java
└── Main.java    # ponto de entrada (menu em modo texto)
```

```
sql/        # create_postgresql.sql, create_mysql.sql, insert_dados.sql, testes_select.sql
scripts/    # compilar / executar / criar banco (.sh e .bat)
docs/       # descricao-projeto.md, EQUIPE.md, diagramas
lib/        # drivers JDBC (se não usar Maven)
```

## 5. Como executar

### 5.1 Aplicação em modo texto (sem banco de dados)
Linux/macOS:
```bash
./scripts/compilar.sh
./scripts/executar.sh
```
Windows:
```bat
scripts\compilar.bat
scripts\executar.bat
```
Ou manualmente:
```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out Main
```

### 5.2 Banco de dados
PostgreSQL:
```bash
./scripts/banco-postgresql.sh      # cria banco, tabelas, dados e testes
```
MySQL:
```bash
./scripts/banco-mysql.sh
```
Depois, em `src/main/java/ConexaoBD.java`, escolha `SGBD_ATIVO` (`POSTGRESQL` ou `MYSQL`) e ajuste usuário/senha.

### 5.3 Teste do CRUD com JDBC
```bash
./scripts/executar.sh TesteCRUD
```

### 5.4 Com Maven
```bash
mvn compile
mvn package
```

## 6. Situação do projeto

## 7. Apresentação
Apresentação em **27/07**, com demonstração do sistema em execução e explicação do código por todos os integrantes.
