#!/usr/bin/env bash
# Cria o banco no MySQL, as tabelas, os dados de exemplo e roda as consultas de teste.
# Requer: cliente mysql instalado. Será pedida a senha do usuário.
set -e
cd "$(dirname "$0")/.."
DB="${DB_NAME:-cetam_projeto}"; USR="${DB_USER:-root}"
mysql -u "$USR" -p -e "CREATE DATABASE IF NOT EXISTS $DB CHARACTER SET utf8mb4;"
mysql -u "$USR" -p "$DB" < sql/create_mysql.sql
mysql -u "$USR" -p "$DB" < sql/insert_dados.sql
mysql -u "$USR" -p "$DB" < sql/testes_select.sql
