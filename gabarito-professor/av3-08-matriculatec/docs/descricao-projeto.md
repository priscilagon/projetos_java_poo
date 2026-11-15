# MatriculaTec — Sistema de Matrícula de Cursos Técnicos do CETAM

## Contexto
O CETAM oferece diversos cursos técnicos (Informática, Sistemas, entre outros) em Manaus e polos do interior. A gestão de matrículas, turmas e disciplinas por módulo demanda organização.

## Objetivo
Construir um sistema de matrícula que gerencie cursos, módulos, disciplinas, turmas e alunos, controlando a situação de matrícula (ativa, trancada, concluída).

## Requisitos funcionais
1. Cadastrar cursos técnicos com seus módulos e disciplinas.
2. Cadastrar turmas vinculadas a um módulo, com limite de vagas.
3. Matricular alunos em turmas, respeitando o limite de vagas.
4. Registrar situação da matrícula (ativa, trancada, concluída, cancelada).
5. Calcular a taxa de ocupação de cada turma.
6. Listar alunos aptos a avançar de módulo (regra de negócio simples).

## Classes sugeridas
- Curso (nome, cargaHoraria, listaModulos)
- Modulo (nome, listaDisciplinas)
- Disciplina (nome, cargaHoraria)
- Turma (modulo, limiteVagas, listaMatriculas)
- Matricula (aluno, turma, situacao) — enum SituacaoMatricula
- Aluno (nome, matrícula, dataNascimento)

## Conceitos de POO a aplicar
- Composição (Curso → Módulo → Disciplina)
- Uso de enum para SituacaoMatricula
- Encapsulamento do controle de vagas
- Coleções (List<Matricula>, Map<Turma, List<Aluno>>)
