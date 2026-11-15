# GuardiãoIgarapé — Gestão de Voluntariado Ambiental

> **Modelo para o grupo.** Preencha as seções marcadas com `<!-- PREENCHER -->`, implemente o projeto em `src/main/java` e mantenha este README atualizado.

**Curso Técnico em Informática — CETAM** · Linguagem de Programação III (Java / POO) · Avaliação AV3

## 1. Descrição do sistema
Desenvolver um sistema que gerencie voluntários, ações ambientais (mutirões de limpeza, plantio de mudas) e o registro de participação, calculando horas de voluntariado acumuladas.

**Contexto:** ONGs e projetos de educação ambiental em Manaus atuam na despoluição e monitoramento de igarapés urbanos (como o Igarapé do Quarenta e do Mindu). A organização de voluntários e ações de limpeza exige controle de participação.

### Requisitos funcionais
1. Cadastrar voluntários com dados pessoais e disponibilidade.
2. Cadastrar ações ambientais (tipo, local/igarapé, data, duração em horas).
3. Registrar a participação de voluntários em cada ação.
4. Calcular o total de horas de voluntariado por pessoa.
5. Emitir certificado (texto) para voluntários que atingirem carga horária mínima.
6. Relatório de ações por tipo (limpeza, plantio, educação ambiental).

### Conceitos de POO aplicados
- Herança em AcaoAmbiental e subtipos
- Interface GeradorCertificado com implementação específica
- Encapsulamento do cálculo de horas acumuladas
- Coleções (Map<Voluntario, Double> para totais de horas)

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
