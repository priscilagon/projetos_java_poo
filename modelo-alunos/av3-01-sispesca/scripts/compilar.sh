#!/usr/bin/env bash
# Compila todo o código de src/ para a pasta out/
set -e
cd "$(dirname "$0")/.."
rm -rf out && mkdir -p out
javac -encoding UTF-8 -cp "lib/*" -d out $(find src -name "*.java")
echo "Compilação concluída em ./out"
