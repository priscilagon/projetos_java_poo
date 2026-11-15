package entity;


/**
 * Entidade JDBC correspondente à tabela "curso".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Curso {

    private int id;
    private String nome;
    private int cargaHoraria;

    public Curso() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Curso(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso{id=" + id + "}";
    }
}
