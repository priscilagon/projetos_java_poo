#!/usr/bin/env bash
# Cria o banco no PostgreSQL, as tabelas, os dados de exemplo e roda as consultas de teste.
# Requer: psql instalado e usuário com permissão (padrão: postgres).
set -e
cd "$(dirname "$0")/.."
DB="${DB_NAME:-cetam_projeto}"; USR="${DB_USER:-postgres}"
psql -U "$USR" -c "CREATE DATABASE $DB;" || echo "(banco já existe)"
psql -U "$USR" -d "$DB" -f sql/create_postgresql.sql
psql -U "$USR" -d "$DB" -f sql/insert_dados.sql
psql -U "$USR" -d "$DB" -f sql/testes_select.sql
