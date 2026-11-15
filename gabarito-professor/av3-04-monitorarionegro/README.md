# MonitoraRioNegro — Monitoramento de Níveis de Cheia e Seca

> **Gabarito de referência da professora (Profa. Priscila Gonçalves).** Não distribuir aos alunos antes da avaliação.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Desenvolver um sistema que registre medições diárias do nível do rio em pontos de monitoramento, classifique o estado (cheia, seca, normal) e alerte quando níveis críticos forem atingidos.

**Contexto:** O Rio Negro, monitorado historicamente pelo Porto de Manaus e por instituições como o INPA, apresenta ciclos anuais de cheia e seca que afetam a vida ribeirinha. Um sistema de acompanhamento ajuda comunidades a se planejarem.

### Requisitos funcionais
1. Cadastrar estações de monitoramento (nome, localização/município).
2. Registrar medições diárias de nível (em metros) por estação.
3. Classificar automaticamente o estado do rio conforme faixas de referência.
4. Emitir alerta quando o nível ultrapassar limites críticos (cheia/seca extrema).
5. Gerar histórico e variação do nível em um período (gráfico textual ou tabela).
6. Comparar dados entre duas ou mais estações.

### Conceitos de POO aplicados
- Interface ClassificadorNivel implementada por diferentes estratégias de classificação
- Encapsulamento de faixas de referência
- Coleções (List<Medicao> ordenada por data)
- Tratamento de exceções para medições inválidas

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
