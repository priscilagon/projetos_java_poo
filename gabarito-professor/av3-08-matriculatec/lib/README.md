# lib/

Coloque aqui os drivers JDBC **somente se não usar Maven**:

- PostgreSQL: `postgresql-42.x.x.jar` — https://jdbc.postgresql.org/download/
- MySQL: `mysql-connector-j-8.x.x.jar` — https://dev.mysql.com/downloads/connector/j/

Os arquivos `.jar` são ignorados pelo Git (`.gitignore`). Cada integrante baixa o seu.
Com Maven (`pom.xml`), os drivers são baixados automaticamente.
