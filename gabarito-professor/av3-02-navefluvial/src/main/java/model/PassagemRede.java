package model;

/** Passagem em rede: opção mais econômica, sem acréscimo sobre o preço base. */
public class PassagemRede extends Passagem {

    public PassagemRede(Passageiro passageiro, double precoBase) {
        super(passageiro, precoBase);
    }

    @Override
    public double calcularValor() {
        return getPrecoBase(); // sem acréscimo
    }
}
