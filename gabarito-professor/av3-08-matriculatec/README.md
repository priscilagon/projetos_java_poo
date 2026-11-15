# MatriculaTec — Sistema de Matrícula de Cursos Técnicos do CETAM

> **Gabarito de referência da professora (Profa. Priscila Gonçalves).** Não distribuir aos alunos antes da avaliação.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Construir um sistema de matrícula que gerencie cursos, módulos, disciplinas, turmas e alunos, controlando a situação de matrícula (ativa, trancada, concluída).

**Contexto:** O CETAM oferece diversos cursos técnicos (Informática, Sistemas, entre outros) em Manaus e polos do interior. A gestão de matrículas, turmas e disciplinas por módulo demanda organização.

### Requisitos funcionais
1. Cadastrar cursos técnicos com seus módulos e disciplinas.
2. Cadastrar turmas vinculadas a um módulo, com limite de vagas.
3. Matricular alunos em turmas, respeitando o limite de vagas.
4. Registrar situação da matrícula (ativa, trancada, concluída, cancelada).
5. Calcular a taxa de ocupação de cada turma.
6. Listar alunos aptos a avançar de módulo (regra de negócio simples).

### Conceitos de POO aplicados
- Composição (Curso → Módulo → Disciplina)
- Uso de enum para SituacaoMatricula
- Encapsulamento do controle de vagas
- Coleções (List<Matricula>, Map<Turma, List<Aluno>>)

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
