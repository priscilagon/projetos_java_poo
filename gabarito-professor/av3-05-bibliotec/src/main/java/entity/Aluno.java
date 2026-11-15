package entity;


/**
 * Entidade JDBC correspondente à tabela "aluno".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Aluno {

    private int id;
    private String nome;
    private String matricula;
    private String curso;
    private String turma;

    public Aluno() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Aluno(String nome, String matricula, String curso, String turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turma = turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno{id=" + id + "}";
    }
}
