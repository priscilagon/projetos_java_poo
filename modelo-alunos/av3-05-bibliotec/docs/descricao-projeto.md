# BiblioTec — Sistema de Biblioteca do CETAM

## Contexto
A biblioteca do CETAM disponibiliza acervo técnico para os cursos de Informática, Sistemas e áreas afins. O controle manual de empréstimos gera atrasos e perda de rastreabilidade dos exemplares.

## Objetivo
Implementar um sistema de biblioteca que controle o acervo, cadastro de alunos e o ciclo de empréstimo/devolução, com cálculo automático de multa por atraso.

## Requisitos funcionais
1. Cadastrar livros/exemplares com categoria (técnico, literatura, referência).
2. Cadastrar alunos com curso e turma.
3. Registrar empréstimo com data prevista de devolução (prazo por categoria).
4. Registrar devolução e calcular multa por dia de atraso.
5. Impedir novo empréstimo para aluno com pendências.
6. Relatório de livros mais emprestados e alunos com atraso.

## Classes sugeridas
- Livro (título, autor, categoria, exemplaresDisponiveis)
- Aluno (nome, matrícula, curso, turma)
- Emprestimo (livro, aluno, dataEmprestimo, dataPrevista, dataDevolucao)
- CalculadoraMulta (interface: calcular(diasAtraso))
- Acervo (gerencia coleção de livros)

## Conceitos de POO a aplicar
- Encapsulamento do estado do exemplar (disponível/emprestado)
- Interface para regras de multa por categoria
- Coleções (Map<Livro, Integer> para controle de exemplares)
- Tratamento de exceções (empréstimo indevido)
