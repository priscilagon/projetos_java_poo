package model;

/**
 * Classe abstrata que representa um criadouro de peixes (tanque-rede ou viveiro).
 * Demonstra HERANÇA: TanqueRede e Viveiro especializam este tipo.
 * Demonstra POLIMORFISMO: cada subclasse calcula sua capacidade máxima de peixes
 * de forma diferente, por meio do método abstrato calcularCapacidadeMaxima().
 */
public abstract class Criadouro {

    // Encapsulamento: atributos privados, acessados apenas por getters/setters
    private String codigo;
    private String especieCriada;

    public Criadouro(String codigo, String especieCriada) {
        this.codigo = codigo;
        this.especieCriada = especieCriada;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEspecieCriada() {
        return especieCriada;
    }

    public void setEspecieCriada(String especieCriada) {
        this.especieCriada = especieCriada;
    }

    /**
     * Método abstrato: cada subclasse implementa sua própria regra de
     * cálculo de capacidade máxima de alevinos suportada. Isso é o
     * ponto central do polimorfismo do projeto.
     */
    public abstract int calcularCapacidadeMaxima();

    @Override
    public String toString() {
        return String.format("[%s] %s - Espécie: %s - Capacidade máx.: %d",
                getClass().getSimpleName(), codigo, especieCriada, calcularCapacidadeMaxima());
    }
}
