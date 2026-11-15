#!/usr/bin/env bash
# =====================================================================
# publicar-github.sh — cria UM repositório GitHub por projeto (AV3 CETAM)
#
# Uso:   ./publicar-github.sh <modelo-alunos|gabarito-professor> <dono-github> [public|private]
# Ex.:   ./publicar-github.sh modelo-alunos cetam-ltp3-2026 private
#
# Requisitos: git e GitHub CLI (gh) instalados e autenticados (gh auth login).
# O <dono-github> pode ser sua conta ou uma organização (recomendado: uma
# organização da turma, onde cada grupo recebe acesso ao repositório do seu projeto).
# Rode a partir da pasta onde está este script. Nada é enviado sem você executá-lo.
# =====================================================================
set -euo pipefail

TIPO="${1:?Informe: modelo-alunos ou gabarito-professor}"
DONO="${2:?Informe o usuário ou organização do GitHub}"
VIS="${3:-private}"

[[ "$TIPO" == "modelo-alunos" || "$TIPO" == "gabarito-professor" ]] || { echo "Tipo inválido: $TIPO"; exit 1; }
command -v gh  >/dev/null || { echo "Instale o GitHub CLI: https://cli.github.com"; exit 1; }
gh auth status >/dev/null 2>&1 || { echo "Execute antes: gh auth login"; exit 1; }

# Gabarito nunca deve ser público
if [[ "$TIPO" == "gabarito-professor" && "$VIS" == "public" ]]; then
  echo "O gabarito não pode ser público. Use private."; exit 1
fi

BASE="$(cd "$(dirname "$0")" && pwd)/$TIPO"
for dir in "$BASE"/*/; do
  nome="$(basename "$dir")"
  [[ "$TIPO" == "gabarito-professor" ]] && repo="${nome}-gabarito" || repo="$nome"
  echo ">>> $DONO/$repo ($VIS)"
  (
    cd "$dir"
    [ -d .git ] || git init -q -b main
    git add -A
    git commit -q -m "Estrutura inicial do projeto ${nome} (AV3 - CETAM)" || echo "(nada a commitar)"
    if gh repo view "$DONO/$repo" >/dev/null 2>&1; then
      echo "    repositório já existe — apenas atualizando"
      git remote get-url origin >/dev/null 2>&1 || git remote add origin "https://github.com/$DONO/$repo.git"
      git push -u origin main
    else
      gh repo create "$DONO/$repo" "--$VIS" --source=. --remote=origin --push \
        --description "AV3 CETAM - Linguagem de Programação III - ${nome}"
    fi
  )
done
echo "Concluído."
