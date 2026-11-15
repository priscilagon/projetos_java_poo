package entity;


/**
 * Entidade JDBC correspondente à tabela "disciplina".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Disciplina {

    private int id;
    private int moduloId;
    private String nome;
    private int cargaHoraria;

    public Disciplina() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Disciplina(int moduloId, String nome, int cargaHoraria) {
        this.moduloId = moduloId;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuloId() {
        return moduloId;
    }

    public void setModuloId(int moduloId) {
        this.moduloId = moduloId;
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
        return "Disciplina{id=" + id + "}";
    }
}
