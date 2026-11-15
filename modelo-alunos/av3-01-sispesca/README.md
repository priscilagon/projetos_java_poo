# SisPesca — Gestão de Cooperativa de Piscicultura de Tambaqui

> **Modelo para o grupo.** Preencha as seções marcadas com `<!-- PREENCHER -->`, implemente o projeto em `src/main/java` e mantenha este README atualizado.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Desenvolver um sistema que controle o cadastro de piscicultores, tanques/viveiros, lotes de peixes, ciclos de alimentação e biometria (peso/tamanho), calculando a taxa de conversão alimentar de cada lote.

**Contexto:** As cooperativas de piscicultura no Amazonas (como as do município de Rio Preto da Eva e Manacapuru) movimentam a criação de tambaqui, pirarucu e matrinxã em tanques-rede e viveiros escavados. O controle manual de lotes, alimentação e biometria gera perdas de produtividade.

### Requisitos funcionais
1. Cadastrar piscicultores cooperados e suas propriedades.
2. Cadastrar tanques-rede/viveiros com capacidade e espécie criada.
3. Registrar lotes de peixes com data de povoamento e quantidade de alevinos.
4. Registrar biometrias periódicas (peso médio, comprimento) por lote.
5. Calcular a taxa de conversão alimentar (ração consumida / ganho de peso).
6. Gerar relatório de produtividade por tanque e por cooperado.

### Conceitos de POO aplicados
- Herança (Viveiro e TanqueRede especializando Criadouro)
- Encapsulamento (atributos privados com getters/setters)
- Polimorfismo (métodos de cálculo de capacidade sobrescritos)
- Coleções (List<LotePeixe>, List<Biometria>)

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
