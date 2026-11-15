# NaveFluvial — Controle de Frota de Transporte Fluvial de Passageiros

> **Modelo para o grupo.** Preencha as seções marcadas com `<!-- PREENCHER -->`, implemente o projeto em `src/main/java` e mantenha este README atualizado.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Criar um sistema de controle de frota fluvial que gerencie embarcações, rotas, viagens e a venda de passagens (rede ou camarote), calculando a ocupação de cada viagem.

**Contexto:** O deslocamento entre Manaus e municípios do interior (Parintins, Tefé, Itacoatiara) é feito majoritariamente por barcos regionais e lanchas rápidas. Empresas de navegação precisam controlar embarcações, viagens e ocupação de camarotes/redes.

### Requisitos funcionais
1. Cadastrar embarcações (nome, capacidade de redes, capacidade de camarotes).
2. Cadastrar rotas (origem, destino, distância em km, duração estimada).
3. Agendar viagens vinculando embarcação e rota, com data/hora de partida.
4. Vender passagens (tipo rede ou camarote) associadas a uma viagem.
5. Impedir venda acima da capacidade da embarcação (regra de negócio).
6. Relatório de taxa de ocupação por viagem e por rota.

### Conceitos de POO aplicados
- Herança e classe abstrata Passagem
- Polimorfismo no cálculo de preço por tipo de passagem
- Encapsulamento das regras de capacidade
- Composição (Viagem possui lista de Passagem)

## 2. Equipe
<!-- PREENCHER: nome completo de TODOS os integrantes (4 a 5 alunos) -->
| # | Nome completo | Usuário GitHub |
|---|---|---|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |

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
<!-- PREENCHER: o que já está pronto e o que falta -->

## 7. Apresentação
Apresentação em **27/07**, com demonstração do sistema em execução e explicação do código por todos os integrantes.
