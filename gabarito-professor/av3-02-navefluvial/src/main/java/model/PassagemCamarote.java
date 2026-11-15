package model;

/** Passagem em camarote: acréscimo de 80% sobre o preço base pelo conforto extra. */
public class PassagemCamarote extends Passagem {

    private static final double PERCENTUAL_ACRESCIMO = 0.80;

    public PassagemCamarote(Passageiro passageiro, double precoBase) {
        super(passageiro, precoBase);
    }

    @Override
    public double calcularValor() {
        return getPrecoBase() * (1 + PERCENTUAL_ACRESCIMO);
    }
}
