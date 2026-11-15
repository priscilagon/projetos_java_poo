#!/usr/bin/env bash
# Executa a aplicação (menu em modo texto). Use: ./scripts/executar.sh [ClassePrincipal]
# Exemplos: ./scripts/executar.sh          -> Main
#           ./scripts/executar.sh TesteCRUD -> teste do CRUD com banco de dados
set -e
cd "$(dirname "$0")/.."
[ -d out ] || ./scripts/compilar.sh
java -Dfile.encoding=UTF-8 -cp "out:lib/*" "${1:-Main}"
