package model;

public class Participacao {
    private Voluntario voluntario;
    private AcaoAmbiental acao;

    public Participacao(Voluntario voluntario, AcaoAmbiental acao) {
        this.voluntario = voluntario;
        this.acao = acao;
    }

    public Voluntario getVoluntario() { return voluntario; }
    public AcaoAmbiental getAcao() { return acao; }

    @Override
    public String toString() {
        return voluntario.getNome() + " participou de " + acao;
    }
}
