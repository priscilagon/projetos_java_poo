# BiblioTec — Sistema de Biblioteca do CETAM

> **Gabarito de referência da professora (Profa. Priscila Gonçalves).** Não distribuir aos alunos antes da avaliação.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Implementar um sistema de biblioteca que controle o acervo, cadastro de alunos e o ciclo de empréstimo/devolução, com cálculo automático de multa por atraso.

**Contexto:** A biblioteca do CETAM disponibiliza acervo técnico para os cursos de Informática, Sistemas e áreas afins. O controle manual de empréstimos gera atrasos e perda de rastreabilidade dos exemplares.

### Requisitos funcionais
1. Cadastrar livros/exemplares com categoria (técnico, literatura, referência).
2. Cadastrar alunos com curso e turma.
3. Registrar empréstimo com data prevista de devolução (prazo por categoria).
4. Registrar devolução e calcular multa por dia de atraso.
5. Impedir novo empréstimo para aluno com pendências.
6. Relatório de livros mais emprestados e alunos com atraso.

### Conceitos de POO aplicados
- Encapsulamento do estado do exemplar (disponível/emprestado)
- Interface para regras de multa por categoria
- Coleções (Map<Livro, Integer> para controle de exemplares)
- Tratamento de exceções (empréstimo indevido)

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
