package model;

/** Representa o passageiro que adquire uma passagem. */
public class Passageiro {
    private String nome;
    private String cpf;
    private String documento;

    public Passageiro(String nome, String cpf, String documento) {
        this.nome = nome;
        this.cpf = cpf;
        this.documento = documento;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getDocumento() { return documento; }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
