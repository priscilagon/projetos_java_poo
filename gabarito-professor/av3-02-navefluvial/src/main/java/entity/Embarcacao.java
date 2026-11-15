package entity;


/**
 * Entidade JDBC correspondente à tabela "embarcacao".
 * Mapeamento simples (sem ORM): cada atributo representa uma coluna da tabela.
 */
public class Embarcacao {

    private int id;
    private String nome;
    private int capacidadeRede;
    private int capacidadeCamarote;

    public Embarcacao() {
        // construtor vazio exigido para reconstruir o objeto a partir do ResultSet
    }

    public Embarcacao(String nome, int capacidadeRede, int capacidadeCamarote) {
        this.nome = nome;
        this.capacidadeRede = capacidadeRede;
        this.capacidadeCamarote = capacidadeCamarote;
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

    public int getCapacidadeRede() {
        return capacidadeRede;
    }

    public void setCapacidadeRede(int capacidadeRede) {
        this.capacidadeRede = capacidadeRede;
    }

    public int getCapacidadeCamarote() {
        return capacidadeCamarote;
    }

    public void setCapacidadeCamarote(int capacidadeCamarote) {
        this.capacidadeCamarote = capacidadeCamarote;
    }

    @Override
    public String toString() {
        return "Embarcacao{id=" + id + "}";
    }
}
